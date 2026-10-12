<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:if test="${not empty sessionScope.message}">
    <p class="succes"><c:out value="${sessionScope.message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.messageErreur}">
    <p class="erreur"><c:out value="${sessionScope.messageErreur}"/></p>
    <c:remove var="messageErreur" scope="session"/>
</c:if>