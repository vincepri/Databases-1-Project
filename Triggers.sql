CREATE OR REPLACE TRIGGER ps_initial
BEFORE INSERT OR UPDATE ON PseudoState
FOR EACH ROW
WHEN (new.StateType = 'Initial')
DECLARE
    x INTEGER;
BEGIN
SELECT COUNT(*) INTO x
    FROM StateVertex S NATURAL JOIN PseudoState P
    WHERE S.SID = (SELECT SID FROM StateVertex S1 WHERE S1.VID = :new.VID);
	IF (x>=1) THEN
    	raise_application_error(-20001, 'Found multiple Initial Vertex.');
	END IF;
END;
/

CREATE OR REPLACE TRIGGER state_final
BEFORE INSERT OR UPDATE ON State
FOR EACH ROW
WHEN (new.isFinal = '1')
DECLARE
    x INTEGER;
    actual_sid StateMachine.SID%TYPE;
BEGIN
    SELECT SID INTO actual_sid
    FROM StateVertex V
    WHERE :new.VID = V.VID;

    SELECT COUNT(*) INTO x
    FROM State S NATURAL JOIN StateVertex V
    WHERE actual_sid = V.SID AND S.isFinal = '1'; 
	
	IF (x>=1) THEN
    	raise_application_error(-20002, 'Found multiple Final states for given SID.');
	END IF;
END;
/

CREATE OR REPLACE TRIGGER ps_FinalTop
BEFORE INSERT OR UPDATE ON PseudoState
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT COUNT(*) INTO x
        FROM State S
		WHERE :new.VID = S.VID AND (S.isFinal = 1 OR S.isTop = 1);
	IF (x>=1) THEN
    	raise_application_error(-20003, 'Given VID is either Final or Top.');
	END IF;
END;
/

CREATE OR REPLACE TRIGGER cs_FinalTop
BEFORE INSERT OR UPDATE ON CompositeState
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT COUNT(*) INTO x
        FROM State S
		WHERE :new.StateID = S.StateID AND (S.isFinal = 1 OR S.isTop = 1);
	IF (x>=1) THEN
    raise_application_error(-20004, 'Given VID is either Final or Top.');
	END IF;
END;
/

CREATE OR REPLACE TRIGGER state_contemp
BEFORE INSERT OR UPDATE ON State
FOR EACH ROW
BEGIN
    IF(:new.isTop = 1 AND :new.isFinal = 1) THEN
        raise_application_error(-20005, 'Given VID is either Final and Top.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER cs_inception
BEFORE INSERT OR UPDATE ON CompositeState
FOR EACH ROW
DECLARE
    actual_sid INTEGER;
BEGIN
    SELECT M.SID INTO actual_sid 
    FROM State S NATURAL JOIN StateVertex V
	WHERE S.StateID = :new.StateID;
	IF(actual_sid = :new.childSID) THEN
        raise_application_error(-20006, 'Child SID cannot be equal to VID SID.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER tr_Top
BEFORE INSERT OR UPDATE ON Transition
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
	FROM State S
	WHERE (:new.Source = S.VID OR :new.target = S.VID) AND S.isTop = 1;
	IF(x>=1) THEN
        raise_application_error(-20007, 'Given Source or Target is a Top State.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER tr_Final
BEFORE INSERT OR UPDATE ON Transition
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
	FROM State S
	WHERE :new.Source = S.VID AND S.isFinal = 1;
	IF(x>=1) THEN
        raise_application_error(-20008, 'Given Source is a Final State.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER cs_pseudo
BEFORE INSERT OR UPDATE ON CompositeState
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(VID) INTO x
	FROM PseudoState P NATURAL JOIN State S
	WHERE S.StateID = :new.StateID;
	IF(x>=1) THEN
        raise_application_error(-20009, 'Pseudo State cannot be a composite state.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER tr_History_Exit
BEFORE INSERT OR UPDATE ON Transition
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
	FROM PseudoState P
	WHERE :new.Source = P.VID AND (P.StateType = 'History' OR P.StateType = 'Exit');
	IF(x>=1) THEN
        raise_application_error(-20010, 'Given Source is either an History Pseudo State or an Exit Pseudo State.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER tr_Initial_Entry
BEFORE INSERT OR UPDATE ON Transition
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
	FROM PseudoState P
	WHERE :new.Target = P.VID AND (P.StateType = 'Initial' OR P.StateType = 'Entry');
	IF(x>=1) THEN
        raise_application_error(-20011, 'Given Source is either an Initial Pseudo State or an Entry Pseudo State.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER tr_Join
BEFORE INSERT OR UPDATE ON Transition
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
	FROM PseudoState P
	WHERE :new.Source = P.VID AND P.StateType = 'Join';
	IF(x>=1) THEN
        raise_application_error(-20012, 'Outgoing transition already exists for given join pseudo state.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER tr_Fork
BEFORE INSERT OR UPDATE ON Transition
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
	FROM PseudoState P
	WHERE :new.Target = P.VID AND P.StateType = 'Fork';
	IF(x>=1) THEN
        raise_application_error(-20013, 'Incoming transition already exists for given fork pseudo state.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER ev_Initial
BEFORE INSERT OR UPDATE ON Event
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
    FROM (State S NATURAL JOIN PseudoState P) JOIN Transition T ON (T.Source = VID)
    WHERE :new.StateID = S.StateID AND P.StateType = 'Initial';
	IF(x>=1) THEN
        raise_application_error(-20014, 'Outgoing transition from Initial Pseudo State can’t have an event.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER ev_Check
BEFORE INSERT OR UPDATE ON Event
FOR EACH ROW
BEGIN
    IF (:new.StateID <> NULL AND :new.TransitionID <> NULL) THEN
        raise_application_error(-20015, 'Given event has both StateID and TransitionID.');
    ELSIF (:new.StateID = NULL AND :new.TransitionID = NULL) THEN
        raise_application_error(-20016, 'Given event has both StateID and TransitionID to NULL.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER ac_Check
BEFORE INSERT OR UPDATE ON Action
FOR EACH ROW
BEGIN
    IF (:new.StateID <> NULL AND :new.TransitionID <> NULL) THEN
        raise_application_error(-20018, 'Given event has both StateID and TransitionID.');
    ELSIF (:new.StateID = NULL AND :new.TransitionID = NULL) THEN
        raise_application_error(-20019, 'Given event has both StateID and TransitionID to NULL.');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER ps_Check
BEFORE INSERT OR UPDATE ON PseudoState
FOR EACH ROW
DECLARE
    x INTEGER;
BEGIN
    SELECT Count(*) INTO x
	FROM TPseudo TP
	WHERE :new.StateType = TP.Name;
	IF(x = 0) THEN
        raise_application_error(-200020, 'The StateType specified is not a valid pseudo state.');
END IF;
END;
/