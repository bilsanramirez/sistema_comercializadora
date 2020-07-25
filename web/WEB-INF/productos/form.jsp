<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>${tipoForm} producto</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">${tipoForm} producto</h3>

                <div class="card-tools">
                    <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                        <i class="fas fa-minus"></i></button>
                    <button type="button" class="btn btn-tool" data-card-widget="remove" data-toggle="tooltip" title="Remove">
                        <i class="fas fa-times"></i></button>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" role="form" action="productos" method="post"> 
                   
                    <input type="hidden" name="accion" value="${tipoForm}">
                    <c:if test="${tipoForm == 'actualizar'}">
                        <input type="hidden" name="idProd"  value="${producto.productoId}">
                        <c:set var="categoriaProductoId" value="${producto.categoriaId.categoriaId}"></c:set>
                        <c:set var="proveedorProductoId" value="${producto.proveedorId.proveedorId}"></c:set>
                    </c:if>
                    <div class="form-group">
                        <label for="proveedorId" class="col-sm-2 control-label">Proveedor</label>
                        <div class="col-sm-10">
                            <select name="proveedorId" id="proveedorId" class="form-control">
                                <c:forEach items="${proveedores}" var="proveedor" varStatus="status">
                                    <option <c:if test="${ tipoForm == 'actualizar' and proveedorProductoId == proveedor.proveedorId }"> selected </c:if>value="${proveedor.proveedorId}">${proveedor.nombreProv}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="categoriaId" class="col-sm-2 control-label">Categoria</label>
                        <div class="col-sm-10">
                            <select name="categoriaId" id="categoriaId" class="form-control">
                                <c:forEach items="${categorias}" var="categoria" varStatus="status">
                                    <option <c:if test="${ tipoForm == 'actualizar' and categoriaProductoId == categoria.categoriaId }"> selected </c:if> value="${categoria.categoriaId}">${categoria.nombreCat}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descripcion" class="col-sm-2 control-label">Descripcion</label>
                        <div class="col-sm-10">
                            <input type="text" name="descripcion" id="descripcion" class="form-control" <c:if test="${tipoForm == 'actualizar'}"> value="${producto.descripcion}"</c:if>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="precioUnit" class="col-sm-2 control-label">Precio unitario</label>
                            <div class="col-sm-10">
                                <input type="number" name="precioUnit" id="precioUnit" class="form-control" <c:if test="${tipoForm == 'actualizar'}"> value="${producto.precioUnit}"</c:if>/>
                            </div>
                        </div
                        <div class="form-group">
                            <label for="existencia" class="col-sm-2 control-label">Existencia</label>
                            <div class="col-sm-10">
                                <input type="number" name="existencia" id="existencia" class="form-control" <c:if test="${tipoForm == 'actualizar'}"> value="${producto.existencia}"</c:if>/>
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