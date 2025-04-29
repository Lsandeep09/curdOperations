<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<%@ page isELIgnored="false" %>

<h1 style="color:blue;text-align:center">Search Data Dynamically</h1>

<frm:form action="search" modelAttribute="emp">
  <table align="center" bgcolor="grey">
    <tr>
      <td>Emp Name:</td>
      <td><frm:input path="ename"/></td>
    </tr>
    <tr>
      <td>Designation:</td>
      <td><frm:input path="job"/></td>
    </tr>
    <tr>
      <td>Salary:</td>
      <td><frm:input path="sal"/></td>
    </tr>
    <tr>
      <td>Dept No:</td>
      <td>
        <frm:select path="deptno">
          <frm:option value="">--Select deptNo--</frm:option>
          <frm:option value="10">10</frm:option>
          <frm:option value="20">20</frm:option>
          <frm:option value="30">30</frm:option>
          <frm:option value="40">40</frm:option>
          <frm:option value="50">50</frm:option>
        </frm:select>
      </td>
    </tr>
    <tr>
      <td><input type="submit" value="Search with Data"/></td>
      <td><input type="reset" value="Cancel"/></td>
    </tr>
  </table>
</frm:form>

<c:choose>
  <c:when test="${!empty empsList}">
    <h1 style="color:red;text-align:center">Employee Report</h1>
    <table border="1" align="center" bgcolor="yellow">
      <tr bgcolor="cyan">
        <th>Emp No</th><th>Emp Name</th><th>Job</th><th>Salary</th><th>Dept No</th><th>Operations</th>
      </tr>
      <c:forEach var="emp" items="${empsList}">
        <tr style="color:blue">
          <td>${emp.empno}</td>
          <td>${emp.ename}</td>
          <td>${emp.job}</td>
          <td>${emp.sal}</td>
          <td>${emp.deptno}</td>
          <td>
            <a href="emp_edit?no=${emp.empno}">
              <img src="images/edit_user.jpeg" width="30px" height="30px"/>
            </a>
            <a href="emp_delete?no=${emp.empno}" onclick="return confirm('Do you want to delete the Employee?')">
              <img src="images/deleteuser.png" width="30px" height="30px"/>
            </a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:when>
  <c:otherwise>
    <h1 style="color:red;text-align:center">Employees Not Found</h1>
  </c:otherwise>
</c:choose>

<br/>

<center>
  <a href="emp_add">
    <img src="images/add.png" width="40px" height="50px"/>Add Employee
  </a>&nbsp;&nbsp;&nbsp;
  <a href="./">
    Home<img src="images/home.png" width="40px" height="50px"/>
  </a>
  <h2 style="color:green;text-align:center">${resultMsg}</h2>
</center>
