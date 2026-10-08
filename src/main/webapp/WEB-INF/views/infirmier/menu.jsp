<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="menu">
    <span class="titre">Espace infirmier</span>
    <a href="${pageContext.request.contextPath}/infirmier/accueil">Accueil</a>
    <a href="${pageContext.request.contextPath}/infirmier/patients/recherche">Accueillir un patient</a>
    <a href="${pageContext.request.contextPath}/infirmier/patients/liste">Patients du jour</a>
    <span class="espace"></span>
    <span><c:out value="${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}"/></span>
    <form method="post" action="${pageContext.request.contextPath}/logout" class="inline">
        <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">
        <button type="submit">Se déconnecter</button>
    </form>
</nav>