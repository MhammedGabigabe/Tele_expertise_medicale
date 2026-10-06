<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Espace infirmier</title>
</head>
<body>
<h1>Espace infirmier</h1>
<p>Bonjour <c:out value="${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}"/>
    (<c:out value="${sessionScope.utilisateur.role.libelle}"/>)</p>
<a href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
</body>
</html>