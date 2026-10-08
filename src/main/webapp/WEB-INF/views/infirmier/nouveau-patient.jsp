<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Nouveau patient</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/infirmier/menu.jsp"/>

<div class="contenu">
    <h1>Nouveau patient</h1>

    <c:if test="${not empty erreur}">
        <p class="erreur"><c:out value="${erreur}"/></p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/infirmier/patients/nouveau">
        <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">

        <fieldset>
            <legend>Identité</legend>
            <label>Nom *
                <input type="text" name="nom" value="<c:out value='${param.nom}'/>" required>
            </label>
            <label>Prénom *
                <input type="text" name="prenom" value="<c:out value='${param.prenom}'/>" required>
            </label>
            <label>Date de naissance *
                <input type="date" name="dateNaissance" value="<c:out value='${param.dateNaissance}'/>" required>
            </label>
            <label>Numéro de sécurité sociale *
                <input type="text" name="numeroSecuriteSociale" value="<c:out value='${param.numeroSecuriteSociale}'/>" required>
            </label>
            <label>Téléphone
                <input type="text" name="telephone" value="<c:out value='${param.telephone}'/>">
            </label>
            <label>Adresse
                <input type="text" name="adresse" value="<c:out value='${param.adresse}'/>">
            </label>
            <label>Mutuelle
                <input type="text" name="mutuelle" value="<c:out value='${param.mutuelle}'/>">
            </label>
        </fieldset>

        <fieldset>
            <legend>Données médicales</legend>
            <label>Antécédents
                <textarea name="antecedents" rows="3"><c:out value="${param.antecedents}"/></textarea>
            </label>
            <label>Allergies
                <textarea name="allergies" rows="2"><c:out value="${param.allergies}"/></textarea>
            </label>
            <label>Traitements en cours
                <textarea name="traitementsEnCours" rows="2"><c:out value="${param.traitementsEnCours}"/></textarea>
            </label>
        </fieldset>

        <fieldset>
            <legend>Signes vitaux</legend>
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
    </form>
</div>
</body>
</html>