<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<fieldset>
    <legend>Dossier du patient</legend>
    <p><strong><c:out value="${patient.prenom} ${patient.nom}"/></strong></p>
    <p>Date de naissance : <c:out value="${patient.dateNaissance}"/></p>
    <p>N° de sécurité sociale : <c:out value="${patient.numeroSecuriteSociale}"/></p>
    <p>Mutuelle : <c:out value="${patient.mutuelle}" default="-"/></p>
    <p>Antécédents : <c:out value="${patient.antecedents}" default="-"/></p>
    <p>Allergies : <c:out value="${patient.allergies}" default="-"/></p>
    <p>Traitements en cours : <c:out value="${patient.traitementsEnCours}" default="-"/></p>
</fieldset>

<fieldset>
    <legend>Signes vitaux</legend>
    <c:choose>
        <c:when test="${not empty signes}">
            <p>Tension artérielle : <c:out value="${signes.tensionArterielle}" default="-"/></p>
            <p>Fréquence cardiaque : <c:out value="${signes.frequenceCardiaque}" default="-"/></p>
            <p>Température : <c:out value="${signes.temperature}" default="-"/> °C</p>
            <p>Fréquence respiratoire : <c:out value="${signes.frequenceRespiratoire}" default="-"/></p>
            <p>Poids : <c:out value="${signes.poids}" default="-"/> kg</p>
            <p>Taille : <c:out value="${signes.taille}" default="-"/> cm</p>
        </c:when>
        <c:otherwise>
            <p>Aucun relevé de signes vitaux.</p>
        </c:otherwise>
    </c:choose>
</fieldset>