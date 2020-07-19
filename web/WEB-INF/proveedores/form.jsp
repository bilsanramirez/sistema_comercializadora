<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>${tipoForm} proveedor</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">${tipoForm} proveedor</h3>

                <div class="card-tools">
                    <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                        <i class="fas fa-minus"></i></button>
                    <button type="button" class="btn btn-tool" data-card-widget="remove" data-toggle="tooltip" title="Remove">
                        <i class="fas fa-times"></i></button>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" role="form" action="proveedores" method="post"> 
                    <input type="hidden" name="accion" value="${tipoForm}">
                    <c:if test="${tipoForm == 'actualizar'}">
                        <input type="hidden" name="idProv"  value="${proveedor.proveedorId}">
                    </c:if>
                    <div class="form-group">
                        <label for="nombreProv" class="col-sm-2 control-label">Nombre</label>
                        <div class="col-sm-10">
                            <input type="text" name="nombreProv" class="form-control" id="nombreProv" <c:if test="${tipoForm == 'actualizar'}">value="${proveedor.nombreProv}"</c:if> placeholder="Nombre proveedor...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="contacto" class="col-sm-2 control-label">Contacto</label>
                            <div class="col-sm-10">
                                <input type="text" name="contacto" class="form-control" id="contacto" <c:if test="${tipoForm == 'actualizar'}">value="${proveedor.contacto}"</c:if> placeholder="Contacto proveedor...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="celuProv" class="col-sm-2 control-label">Telefono celular</label>
                            <div class="col-sm-10">
                                <input type="text" name="celuProv" class="form-control" id="celuProv" <c:if test="${tipoForm == 'actualizar'}">value="${proveedor.celuProv}"</c:if> placeholder="Celular proveedor...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fijoProv" class="col-sm-2 control-label">Telefono fijo</label>
                            <div class="col-sm-10">
                                <input type="text" name="fijoProv" class="form-control" id="fijoProv" <c:if test="${tipoForm == 'actualizar'}">value="${proveedor.fijoProv}"</c:if> placeholder="Fijo proveedor...">
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