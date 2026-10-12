<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes consultations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/generaliste/menu.jsp"/>

<div class="contenu">
    <h1>Mes consultations</h1>

    <jsp:include page="/WEB-INF/views/fragments/message.jsp"/>

    <c:choose>
        <c:when test="${empty consultations}">
            <p>Aucune consultation pour le moment.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>N°</th>
                    <th>Patient</th>
                    <th>Motif</th>
                    <th>Statut</th>
                    <th></th>
                </tr>
                <c:forEach var="c" items="${consultations}">
                    <tr>
                        <td><c:out value="${c.id}"/></td>
                        <td><c:out value="${c.patient.prenom} ${c.patient.nom}"/></td>
                        <td><c:out value="${c.motif}"/></td>
                        <td><c:out value="${c.statut.libelle}"/></td>
                        <td><a class="bouton"
                               href="${pageContext.request.contextPath}/generaliste/consultations/detail?id=${c.id}">Ouvrir</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>