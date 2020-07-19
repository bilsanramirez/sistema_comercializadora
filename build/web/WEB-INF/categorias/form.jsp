<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>${tipoForm} categorias</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">${tipoForm} categoria</h3>

                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fas fa-minus"></i></button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fas fa-times"></i></button>
                    </div>
                </div>
                <div class="card-body">
                    <form class="form-horizontal" role="form" action="categorias" method="post"> 
                        <input type="hidden" name="accion" value="${tipoForm}">
                        <div class="form-group">
                            <label for="categoriaId" class="col-sm-2 control-label">Clave de categoria</label>
                            <div class="col-sm-10">
                                <input type="number" name="categoriaId" class="form-control" id="categoriaId" <c:if test="${tipoForm == 'actualizar'}">value="${categoria.categoriaId}"</c:if> placeholder="Clave de categoria...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombreCat" class="col-sm-2 control-label">Nombre categoria</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="nombreCat" name="nombreCat" <c:if test="${tipoForm == 'actualizar'}">value="${categoria.nombreCat}"</c:if> placeholder="Nombre categoria...">
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