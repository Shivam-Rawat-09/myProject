<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- Add this line for the c:url tag --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
<head>
    <title>PDA Calculator</title>
    <style>
        /* CSS styles remain the same */
        body { font-family: sans-serif; margin: 2em; }
        .container { max-width: 600px; margin: auto; padding: 2em; border: 1px solid #ccc; border-radius: 8px; }
        .form-group { margin-bottom: 1em; }
        label { display: block; margin-bottom: 0.5em; }
        input, select { width: 100%; padding: 8px; box-sizing: border-box; }
        button { padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>

<div class="container">
    <h2>Port Disbursement Account (PDA) Calculator</h2>
    <p>Estimate port call costs for Chennai/Mumbai Port.</p>

    <%-- THIS IS THE CORRECTED LINE --%>
    <c:url var="formActionUrl" value="/calculate" />
    <form:form action="${formActionUrl}" method="post" modelAttribute="pdaForm">
    
    
    <div class="form-group">
        <label for="portName">Select Port:</label>
        <form:select path="portName" id="portName">
            <form:option value="CHENNAI">Chennai Port</form:option>
            <form:option value="MUMBAI">Mumbai Port</form:option>
        </form:select>
    </div>

        <div class="form-group">
            <label for="voyageType">Voyage Type:</label>
            <form:select path="voyageType" id="voyageType">
                <form:option value="FOREIGN">Foreign</form:option>
                <form:option value="COASTAL">Coastal</form:option>
            </form:select>
        </div>

        <div class="form-group">
            <label for="vesselType">Vessel Type:</label>
            <form:select path="vesselType" id="vesselType">
                <form:option value="CONTAINER">Container Vessel</form:option>
                <form:option value="POL_CRUDE">POL / Crude Oil Vessel</form:option>
                <form:option value="RORO">RoRo Vessel</form:option>
                <form:option value="GENERAL_CARGO">General Cargo Vessel</form:option>
            </form:select>
        </div>

        <div class="form-group">
            <label for="grt">Gross Registered Tonnage (GRT):</label>
            <form:input type="number" path="grt" id="grt" required="true"/>
        </div>

        <div class="form-group">
            <label for="berthStayHours">Estimated Berth Stay (in hours):</label>
            <form:input type="number" path="berthStayHours" id="berthStayHours" required="true"/>
        </div>

        <hr/>
        <h4>Cargo Details</h4>

        <div class="form-group">
            <label for="cargoType">Cargo Type:</label>
            <form:select path="cargoType" id="cargoType">
                <form:option value="">-- No Cargo --</form:option>
                <!-- <form:option value="CRUDE_OIL">Crude Oil</form:option> -->
                <form:option value="FERTILIZER">Fertilizer</form:option>
                <form:option value="METAL_SCRAP">Metal Scrap</form:option>
                <!-- <form:option value="IRON_ORE_PELLETS">Iron Ore Pellets</form:option> -->
            </form:select>
        </div>

        <div class="form-group">
            <label for="cargoQuantity">Cargo Quantity (in MT):</label>
            <form:input type="number" step="0.01" path="cargoQuantity" id="cargoQuantity"/>
        </div>

        <button type="submit">Calculate PDA</button>
    </form:form>
</div>

</body>
</html>