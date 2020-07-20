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
                <h3 class="card-title">${tipoForm} Cliente</h3>
                <div class="card-tools">
                    <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                        <i class="fas fa-minus"></i></button>
                    <button type="button" class="btn btn-tool" data-card-widget="remove" data-toggle="tooltip" title="Remove">
                        <i class="fas fa-times"></i></button>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" role="form" action="clientes" method="post"> 
                    <input type="hidden" name="accion" value="${tipoForm}">
                    <c:if test="${tipoForm == 'actualizar'}">
                        <input type="hidden" name="idCli" value="${cliente.clienteId}">
                    </c:if>
                    <div class="form-group">
                        <label for="cedulaRuc" class="col-sm-2 control-label">Cedula ruc</label>
                        <div class="col-sm-10">
                            <input type="text" name="cedulaRuc" class="form-control" id="cedulaRuc" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.cedulaRuc}"</c:if> placeholder="Cedula del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombreCia" class="col-sm-2 control-label">Nombre compañia</label>
                            <div class="col-sm-10">
                                <input type="text" name="nombreCia" class="form-control" id="nombreCia" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.nombreCia}"</c:if> placeholder="nombre del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombreContacto" class="col-sm-2 control-label">Nombre contacto</label>
                            <div class="col-sm-10">
                                <input type="text" name="nombreContacto" class="form-control" id="nombreContacto" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.nombreContacto}"</c:if> placeholder="Contacto del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="direccionCli" class="col-sm-2 control-label">Direccion</label>
                            <div class="col-sm-10">
                                <input type="text" name="direccionCli" class="form-control" id="direccionCli" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.direccionCli}"</c:if> placeholder="Direccion del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fax" class="col-sm-2 control-label">Fax</label>
                            <div class="col-sm-10">
                                <input type="text" name="fax" class="form-control" id="fax" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.fax}"</c:if> placeholder="Fax del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                                <input type="text" name="email" class="form-control" id="email" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.email}"</c:if> placeholder="Email del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="celular" class="col-sm-2 control-label">Celular</label>
                            <div class="col-sm-10">
                                <input type="text" name="celular" class="form-control" id="celular" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.celular}"</c:if> placeholder="Celular del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fijo" class="col-sm-2 control-label">Fijo</label>
                            <div class="col-sm-10">
                                <input type="text" name="fijo" class="form-control" id="fijo" <c:if test="${tipoForm == 'actualizar'}">value="${cliente.fijo}"</c:if> placeholder="Telefono fijo del cliente...">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="submit" class="btn btn-primary" value="${tipoForm}">
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