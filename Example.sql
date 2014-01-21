-- Create StateMachine
INSERT INTO StateMachine VALUES(1, 'smCompound', NULL, '/home/user/smcompound', 'Vincenzo Prignano, Valerio Russo');

-- Create Top State and Initial
INSERT INTO StateVertex VALUES(1, 1, 'smCompoundTop');
INSERT INTO State VALUES(1, 1, 'Package', NULL, 0, 1);
INSERT INTO StateVertex VALUES(7, 1, 'smCompound Initial');
INSERT INTO PseudoState VALUES(7, 'Initial');

-- Create EnterPin Composite State
INSERT INTO StateVertex VALUES(2, 1, 'Check PIN');
INSERT INTO State VALUES(2, 2, 'Package', NULL, 0, 0);
INSERT INTO StateMachine VALUES(2, 'smCheckPIN', NULL, '/home/user/smcompound', 'Vincenzo Prignano, Valerio Russo');
INSERT INTO CompositeState VALUES(2, 1, 2);
INSERT INTO StateVertex VALUES(3, 2, 'Check PIN Initial');
INSERT INTO PseudoState VALUES(3, 'Initial');
INSERT INTO StateVertex VALUES(4, 2, 'Enter PIN');
INSERT INTO State VALUES(3, 4, 'Package', NULL, 0, 0);
INSERT INTO StateVertex VALUES(5, 2, 'Enter PIN Choice');
INSERT INTO PseudoState VALUES(5, 'Choice');
INSERT INTO StateVertex VALUES(6, 2, 'Enter PIN Final');
INSERT INTO State VALUES(4, 6, 'Package', NULL, 1, 0);

-- Other states
INSERT INTO StateVertex VALUES(8, 1, 'Search Network');
INSERT INTO State VALUES(5, 8, 'Package', NULL, 0, 0);
INSERT INTO StateVertex VALUES(9, 1, 'Ready');
INSERT INTO State VALUES(6, 9, 'Package', NULL, 0, 0);
INSERT INTO StateVertex VALUES(10, 1, 'Off');
INSERT INTO State VALUES(7, 10, 'Package', NULL, 0, 0);

-- Create transitions
INSERT ALL
	INTO Transition VALUES(1, NULL, 'Package', 7, 2)
	INTO Transition VALUES(2, NULL, 'Package', 3, 4)
	INTO Transition VALUES(3, NULL, 'Package', 4, 5)
	INTO Transition VALUES(4, NULL, 'Package', 5, 4)
	INTO Transition VALUES(5, NULL, 'Package', 5, 6)
	INTO Transition VALUES(6, NULL, 'Package', 2, 8)
	INTO Transition VALUES(7, 'Network Found', 'Package', 8, 9)
	INTO Transition VALUES(8, 'Power Off', 'Package', 8, 10)
	INTO Transition VALUES(9, 'Power Off', 'Package', 9, 10)
	INTO Transition VALUES(10, 'Power Off', 'Package', 2, 10)
SELECT * FROM dual;
	
-- Insert Guard Conditions, Actions and Events
INSERT ALL
	INTO GuardCondition VALUES(4, 'PIN Invalid')
	INTO GuardCondition VALUES(5, 'PIN OK')
	INTO GuardCondition VALUES(6, 'PIN OK')
	INTO Action VALUES(1, NULL, 3, 'Check PIN', 'Package')
	INTO Event VALUES(1, NULL, 7, 'Network Found', 'Package')
	INTO Event VALUES(2, NULL, 8, 'Power Off', 'Package')
	INTO Event VALUES(3, NULL, 9, 'Power Off', 'Package')
	INTO Event VALUES(4, NULL, 10, 'Power Off', 'Package')
SELECT * FROM dual;