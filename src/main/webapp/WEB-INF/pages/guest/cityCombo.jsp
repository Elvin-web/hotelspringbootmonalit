<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/13/2019
  Time: 2:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row form-group">
    <div class="col col-md-3">
        <label for="select1" class=" form-control-label">City</label>
    </div>
    <div class="col-12 col-md-9">
        <select name="select1" id="select1" class="form-control">
            <option value="0">Please select City</option>
            <c:forEach items="${cityList}" var="cl">
                <option value="${cl.id_city}">${cl.name}</option>
            </c:forEach>
        </select>
    </div>
</div>
