<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Nouvelle consultation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/generaliste/menu.jsp"/>

<div class="contenu">
    <h1>Nouvelle consultation</h1>

    <jsp:include page="/WEB-INF/views/generaliste/dossier.jsp"/>

    <c:if test="${not empty erreur}">
        <p class="erreur"><c:out value="${erreur}"/></p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/generaliste/consultations/nouvelle">
        <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">
        <input type="hidden" name="fileId" value="<c:out value='${entree.id}'/>">

        <fieldset>
            <legend>Consultation</legend>
            <label>Motif *
                <input type="text" name="motif" value="<c:out value='${param.motif}'/>" required>
            </label>
            <label>Observations (examen clinique, symptômes décrits par le patient)
                <textarea name="observations" rows="5"><c:out value="${param.observations}"/></textarea>
            </label>
            <p>Coût de la consultation : <strong>150 DH</strong> (tarif fixe)</p>
        </fieldset>

        <button type="submit">Créer la consultation</button>
        <a class="bouton" href="${pageContext.request.contextPath}/generaliste/file-attente">Annuler</a>
    </form>
</div>
</body>
</html>