<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>PDA Calculation Result</title>
    <style>
        body { font-family: sans-serif; margin: 2em; }
        .container { max-width: 800px; margin: auto; padding: 2em; border: 1px solid #ccc; border-radius: 8px; }
        table { width: 100%; border-collapse: collapse; margin-top: 1em; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .total-row { font-weight: bold; }
        .input-summary { background-color: #eef; padding: 1em; border-radius: 4px; }
    </style>
</head>
<body>

<div class="container">
    <h2>Proforma Disbursement Account (PDA) Estimate</h2>

    <h3>Input Summary</h3>
    <div class="input-summary">
        <p><strong>Port:</strong> ${result.input.portName}</p>
        <p><strong>Voyage Type:</strong> ${result.input.voyageType}</p>
        <p><strong>Vessel Type:</strong> ${result.input.vesselType.replace('_', ' ')}</p>
        <p><strong>GRT:</strong> ${result.input.grt}</p>
        <p><strong>Berth Stay:</strong> ${result.input.berthStayHours} hours</p>
        <c:if test="${not empty result.input.cargoType}">
            <p><strong>Cargo:</strong> ${result.input.cargoType.replace('_', ' ')} (${result.input.cargoQuantity} MT)</p>
        </c:if>
    </div>


    <h3>Cost Breakdown</h3>
    <table>
        <tr>
            <th>Charge Component</th>
            <th>Estimated Cost (${result.currency})</th>
        </tr>
        <c:forEach items="${result.costBreakdown}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}" /></td>
            </tr>
        </c:forEach>
        <tr class="total-row">
            <td>Total Estimated Cost</td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${result.totalCost}" /></td>
        </tr>
    </table>
    
    <p><small><strong>Disclaimer:</strong> This is a proforma estimate based on the provided rates. Actual charges may vary.</small></p>

    <br/>
    <a href="/">Calculate Another PDA</a>
</div>

</body>
</html>