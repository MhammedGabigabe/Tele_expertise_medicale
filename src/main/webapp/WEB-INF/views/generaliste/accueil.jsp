<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Espace généraliste</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/generaliste/menu.jsp"/>

<div class="contenu">
    <h1>Accueil</h1>

    <jsp:include page="/WEB-INF/views/fragments/message.jsp"/>

    <p>Bonjour Dr <c:out value="${sessionScope.utilisateur.nom}"/>, que souhaitez-vous faire ?</p>
    <a class="bouton" href="${pageContext.request.contextPath}/generaliste/file-attente">Voir la file d'attente</a>
    <a class="bouton" href="${pageContext.request.contextPath}/generaliste/consultations">Mes consultations</a>
</div>
</body>
</html>