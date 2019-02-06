CREATE OR REPLACE FUNCTION bc_code 
RETURN CHAR
IS
BEGIN
  RETURN concat('bc_', LPAD(seq_bc.nextval, 6, 0));
END;
/