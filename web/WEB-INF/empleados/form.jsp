<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>${tipoForm} empleado</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">${tipoForm} empleado</h3>

                <div class="card-tools">
                    <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                        <i class="fas fa-minus"></i></button>
                    <button type="button" class="btn btn-tool" data-card-widget="remove" data-toggle="tooltip" title="Remove">
                        <i class="fas fa-times"></i></button>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" role="form" action="empleados" method="post"> 
                    <input type="hidden" name="accion" value="${tipoForm}">
                    <c:if test="${tipoForm == 'actualizar'}">
                        <c:set var="idReportaA" value="${empleado.reportaA}"></c:set>
                        <input type="hidden" name="idEmp" value="${empleado.empleadoId}">
                    </c:if>
                    <div class="form-group">
                        <label for="nombre" class="col-sm-2 control-label">Nombre</label>
                        <div class="col-sm-10">
                            <input type="text" name="nombre" class="form-control" id="nombre" <c:if test="${tipoForm == 'actualizar'}">value="${empleado.nombre}"</c:if> placeholder="Nombre empleado...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="apellido" class="col-sm-2 control-label">Apellido</label>
                            <div class="col-sm-10">
                                <input type="text" name="apellido" class="form-control" id="apellido" <c:if test="${tipoForm == 'actualizar'}">value="${empleado.apellido}"</c:if> placeholder="Apellido empleado...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fechaNac" class="col-sm-2 control-label">Fecha de nacimiento</label>
                            <div class="col-sm-10">
                                <input type="date"  name="fechaNac" class="form-control" id="fechaNac"<c:if test="${tipoForm == 'actualizar'}">value="${empleado.fechaNac}"</c:if>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="reportaA" class="col-sm-2 control-label">Jefe</label>
                            <div class="col-sm-10">
                                <select name="reportaA" id="reportaA" class="form-control">
                                <c:forEach items="${empleados}" var="empleadoJefe" varStatus="status">
                                    <option <c:if test="${idReportaA == empleadoJefe.empleadoId}"><c:out value="selected"></c:out></c:if> value="${empleadoJefe.empleadoId}">${ empleadoJefe.getNombreCompleto()}</option>
                                </c:forEach>
                            </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="extension" class="col-sm-2 control-label">Extension</label>
                            <div class="col-sm-10">
                                <input type="text" name="extension" class="form-control" id="extension" <c:if test="${tipoForm == 'actualizar'}">value="${empleado.extencion}"</c:if> placeholder="Extension empleado...">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">${tipoForm}</button>
                        </div>
                    </div> 
                </form>
            </div>
            <!-- /.card-body -->
            <div class="card-footer">

            </div>
            <!-- /.card-footer-->
        </div>
        <!-- /.card -->

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<c:import url="../layouts/dashboard2.jsp"></c:import>