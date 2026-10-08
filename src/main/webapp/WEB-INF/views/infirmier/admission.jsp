<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Admission du patient</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/infirmier/menu.jsp"/>

<div class="contenu">
    <h1>Patient : <c:out value="${patient.prenom} ${patient.nom}"/></h1>

    <fieldset>
        <legend>Informations du dossier</legend>
        <p>Date de naissance : <c:out value="${patient.dateNaissance}"/></p>
        <p>N° de sécurité sociale : <c:out value="${patient.numeroSecuriteSociale}"/></p>
        <p>Téléphone : <c:out value="${patient.telephone}" default="-"/></p>
        <p>Adresse : <c:out value="${patient.adresse}" default="-"/></p>
        <p>Mutuelle : <c:out value="${patient.mutuelle}" default="-"/></p>
        <p>Antécédents : <c:out value="${patient.antecedents}" default="-"/></p>
        <p>Allergies : <c:out value="${patient.allergies}" default="-"/></p>
        <p>Traitements en cours : <c:out value="${patient.traitementsEnCours}" default="-"/></p>
    </fieldset>

    <c:if test="${not empty erreur}">
        <p class="erreur"><c:out value="${erreur}"/></p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/infirmier/patients/admission">
        <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">
        <input type="hidden" name="id" value="<c:out value='${patient.id}'/>">

        <fieldset>
            <legend>Nouveaux signes vitaux</legend>
            <label>Tension artérielle * (ex : 120/80)
                <input type="text" name="tensionArterielle" value="<c:out value='${param.tensionArterielle}'/>" required>
            </label>
            <label>Fréquence cardiaque * (battements/min)
                <input type="number" name="frequenceCardiaque" value="<c:out value='${param.frequenceCardiaque}'/>" required>
            </label>
            <label>Température * (°C)
                <input type="number" step="0.1" name="temperature" value="<c:out value='${param.temperature}'/>" required>
            </label>
            <label>Fréquence respiratoire * (cycles/min)
                <input type="number" name="frequenceRespiratoire" value="<c:out value='${param.frequenceRespiratoire}'/>" required>
            </label>
            <label>Poids (kg)
                <input type="number" step="0.1" name="poids" value="<c:out value='${param.poids}'/>">
            </label>
            <label>Taille (cm)
                <input type="number" step="0.1" name="taille" value="<c:out value='${param.taille}'/>">
            </label>
        </fieldset>

        <button type="submit">Enregistrer et ajouter à la file d'attente</button>
        <a class="bouton" href="${pageContext.request.contextPath}/infirmier/patients/recherche">Annuler</a>
    </form>
</div>
</body>
</html>