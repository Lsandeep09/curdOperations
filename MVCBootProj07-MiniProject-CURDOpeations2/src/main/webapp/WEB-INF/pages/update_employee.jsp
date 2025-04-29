<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<h1 style ="color:red;text-align:center">Update Employee</h1>
<frm:form modelAttribute="emp">
 <table align="center" bgcolor="cyan">
  <tr>
  <td>Employee Number::</td>
  <td><frm:input path ="empno" readony="true"/></td>
   <td> Employee Name::</td>
   <td><frm:input path="ename"/></td>
    <td> Employee Desg::</td>
    <td> <frm:input path="job"/></td>
  </tr>
  <tr>
   <td>Employee Salary::</td>
   <td><frm:input path="sal"/></td>
  </tr>
  <tr>
   <td>dept no</td>
   <td><frm:input path="deptno"/></td>
  </tr>
  <tr>
   <td><input type="submit"value="Update employee"></td>
   <td><input type="reset" value="cancel"></td>
 </table>
</frm:form>