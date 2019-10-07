create or replace package  HCM_ACTIVITY_PKG as

function is_user_registered (
    p_user in VARCHAR2)
    return boolean;
    
function get_enroll_activity_status(
      p_employee_id in number)
    return VARCHAR2;

procedure SAVE_OR_UPDATE_INVITIED (
     p_activity_id number,
     p_employee_id number);
     

procedure SYN_EMPLOYEE ;

end;



create or replace package body HCM_ACTIVITY_PKG is
-- -----------------------------------------------------------------------------------
-- is_user_registered TODO DELETE
-- -----------------------------------------------------------------------------------
function is_user_registered (
    p_user in VARCHAR2)
    return boolean
as
  v_count NUMBER;
begin
  select count(*) into  v_count from TB_EMPLOYEE where email=p_user and deleted='NO';
  if v_count = 1 then
    return true;
  else
    return false;
  end if;
  
end;
end;
