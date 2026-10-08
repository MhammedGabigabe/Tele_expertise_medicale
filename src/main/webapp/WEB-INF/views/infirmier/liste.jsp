<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Patients du jour</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/infirmier/menu.jsp"/>

<div class="contenu">
    <h1>Patients du jour (<c:out value="${jour}"/>)</h1>

    <c:choose>
        <c:when test="${empty entrees}">
            <p>Aucun patient enregistré aujourd'hui.</p>
        </c:when>
        <c:otherwise>
            <p><c:out value="${entrees.size()}"/> patient(s) enregistré(s)</p>
            <table>
                <tr>
                    <th>Heure</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>N° sécurité sociale</th>
                    <th>Tension</th>
                    <th>FC</th>
                    <th>Temp. (°C)</th>
                    <th>FR</th>
                    <th>Statut</th>
                </tr>
                <c:forEach var="f" items="${entrees}">
                    <tr>
                        <td><c:out value="${f.heureArrivee}"/></td>
                        <td><c:out value="${f.patient.nom}"/></td>
                        <td><c:out value="${f.patient.prenom}"/></td>
                        <td><c:out value="${f.patient.numeroSecuriteSociale}"/></td>
                        <td><c:out value="${f.signesVitaux.tensionArterielle}" default="-"/></td>
                        <td><c:out value="${f.signesVitaux.frequenceCardiaque}" default="-"/></td>
                        <td><c:out value="${f.signesVitaux.temperature}" default="-"/></td>
                        <td><c:out value="${f.signesVitaux.frequenceRespiratoire}" default="-"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${f.consulte}">Pris en charge</c:when>
                                <c:otherwise>En attente</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>