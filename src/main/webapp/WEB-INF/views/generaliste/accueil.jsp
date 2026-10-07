<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Espace généraliste</title>
</head>
<body>
<h1>Espace médecin généraliste</h1>
<p>Bonjour <c:out value="${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}"/>
    (<c:out value="${sessionScope.utilisateur.role.libelle}"/>)</p>

<form method="post" action="${pageContext.request.contextPath}/logout">
    <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">
    <button type="submit">Se déconnecter</button>
</form>
</body>
</html>