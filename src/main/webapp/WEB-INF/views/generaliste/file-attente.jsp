<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>File d'attente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/generaliste/menu.jsp"/>

<div class="contenu">
    <h1>File d'attente</h1>

    <jsp:include page="/WEB-INF/views/fragments/message.jsp"/>

    <c:choose>
        <c:when test="${empty entrees}">
            <p>Aucun patient en attente.</p>
        </c:when>
        <c:otherwise>
            <p><c:out value="${entrees.size()}"/> patient(s) en attente</p>
            <table>
                <tr>
                    <th>Arrivée</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>N° sécurité sociale</th>
                    <th>Tension</th>
                    <th>Temp. (°C)</th>
                    <th></th>
                </tr>
                <c:forEach var="f" items="${entrees}">
                    <tr>
                        <td><c:out value="${f.heureArrivee}"/></td>
                        <td><c:out value="${f.patient.nom}"/></td>
                        <td><c:out value="${f.patient.prenom}"/></td>
                        <td><c:out value="${f.patient.numeroSecuriteSociale}"/></td>
                        <td><c:out value="${f.signesVitaux.tensionArterielle}" default="-"/></td>
                        <td><c:out value="${f.signesVitaux.temperature}" default="-"/></td>
                        <td><a class="bouton"
                               href="${pageContext.request.contextPath}/generaliste/consultations/nouvelle?fileId=${f.id}">Prendre en charge</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>