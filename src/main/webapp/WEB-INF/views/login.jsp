<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion - Télé-expertise médicale</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f2f5f9; display: flex;
               justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .carte { background: white; padding: 30px; border-radius: 8px; width: 320px;
                 box-shadow: 0 2px 8px rgba(0,0,0,0.15); }
        h2 { text-align: center; margin-top: 0; }
        label { display: block; margin-top: 12px; }
        input { width: 100%; padding: 8px; margin-top: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; margin-top: 20px; background: #1976d2;
                 color: white; border: none; border-radius: 4px; cursor: pointer; }
        .erreur { color: #c62828; margin-top: 12px; text-align: center; }
    </style>
</head>
<body>
<div class="carte">
    <h2>Connexion</h2>

    <form method="post" action="${pageContext.request.contextPath}/login">
         <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>

        <label for="motDePasse">Mot de passe</label>
        <input type="password" id="motDePasse" name="motDePasse" required>

        <button type="submit">Se connecter</button>
    </form>

    <c:if test="${not empty erreur}">
        <p class="erreur"><c:out value="${erreur}"/></p>
    </c:if>
</div>
</body>
</html>