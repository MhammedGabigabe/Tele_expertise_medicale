<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Espace infirmier</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/infirmier/menu.jsp"/>

<div class="contenu">
    <h1>Accueil</h1>

    <c:if test="${not empty sessionScope.message}">
        <p class="succes"><c:out value="${sessionScope.message}"/></p>
        <c:remove var="message" scope="session"/>
    </c:if>

    <p>Bonjour <c:out value="${sessionScope.utilisateur.prenom}"/>, que souhaitez-vous faire ?</p>
    <a class="bouton" href="${pageContext.request.contextPath}/infirmier/patients/recherche">Accueillir un patient</a>
</div>
</body>
</html>