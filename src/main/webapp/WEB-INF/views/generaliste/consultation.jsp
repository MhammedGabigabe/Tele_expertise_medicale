<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Consultation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/generaliste/menu.jsp"/>

<div class="contenu">
    <h1>Consultation n° <c:out value="${consultation.id}"/></h1>

    <jsp:include page="/WEB-INF/views/fragments/message.jsp"/>

    <jsp:include page="/WEB-INF/views/generaliste/dossier.jsp"/>

    <fieldset>
        <legend>Consultation</legend>
        <p>Statut : <strong><c:out value="${consultation.statut.libelle}"/></strong></p>
        <p>Motif : <c:out value="${consultation.motif}"/></p>
        <p>Observations : <c:out value="${consultation.observations}" default="-"/></p>
        <p>Coût : <c:out value="${consultation.cout}"/> DH</p>
    </fieldset>

    <c:if test="${not empty erreur}">
        <p class="erreur"><c:out value="${erreur}"/></p>
    </c:if>

    <c:choose>
        <%-- Consultation ouverte : diagnostic, traitement et clôture --%>
        <c:when test="${consultation.statut.name() == 'EN_COURS'}">
            <form method="post" action="${pageContext.request.contextPath}/generaliste/consultations/detail">
                <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">
                <input type="hidden" name="id" value="<c:out value='${consultation.id}'/>">

                <fieldset>
                    <legend>Prise en charge directe</legend>
                    <label>Diagnostic *
                        <textarea name="diagnostic" rows="3" required><c:out value="${param.diagnostic}"/></textarea>
                    </label>
                    <label>Traitement prescrit * (ex : Paracétamol 1 g, 3 fois par jour)
                        <textarea name="traitement" rows="3" required><c:out value="${param.traitement}"/></textarea>
                    </label>
                </fieldset>

                <button type="submit">Clôturer la consultation</button>
            </form>
        </c:when>

        <%-- Consultation terminée : lecture seule --%>
        <c:otherwise>
            <fieldset>
                <legend>Prise en charge</legend>
                <p>Diagnostic : <c:out value="${consultation.diagnostic}" default="-"/></p>
                <p>Traitement : <c:out value="${consultation.traitement}" default="-"/></p>
            </fieldset>
        </c:otherwise>
    </c:choose>

    <p><a class="bouton" href="${pageContext.request.contextPath}/generaliste/consultations">Retour à mes consultations</a></p>
</div>
</body>
</html>