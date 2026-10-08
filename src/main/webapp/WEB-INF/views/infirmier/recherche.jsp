<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Accueillir un patient</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/infirmier/menu.jsp"/>

<div class="contenu">
    <h1>Accueillir un patient</h1>

    <form method="get" action="${pageContext.request.contextPath}/infirmier/patients/recherche">
        <label for="q">Rechercher un patient</label>
        <input type="text" id="q" name="q" value="<c:out value='${param.q}'/>"
               placeholder="N° de sécurité sociale, nom ou prénom" required>
        <br><br>
        <button type="submit">Rechercher</button>
    </form>

    <c:if test="${not empty q}">
        <c:choose>
            <c:when test="${empty patients}">
                <p>Aucun patient trouvé pour « <c:out value="${q}"/> ».</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Date de naissance</th>
                        <th>N° sécurité sociale</th>
                        <th></th>
                    </tr>
                    <c:forEach var="p" items="${patients}">
                        <tr>
                            <td><c:out value="${p.nom}"/></td>
                            <td><c:out value="${p.prenom}"/></td>
                            <td><c:out value="${p.dateNaissance}"/></td>
                            <td><c:out value="${p.numeroSecuriteSociale}"/></td>
                            <td><a class="bouton"
                                   href="${pageContext.request.contextPath}/infirmier/patients/admission?id=${p.id}">Sélectionner</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </c:if>

    <hr>
    <p>Le patient n'a pas de dossier ?</p>
    <a class="bouton" href="${pageContext.request.contextPath}/infirmier/patients/nouveau">Nouveau patient</a>
</div>
</body>
</html>