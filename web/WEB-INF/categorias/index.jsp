<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Categorias</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Lista de Categorias</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                <c:if test="${operacionCategoria != null}">
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h5><i class="icon fas fa-check"></i> Mensaje!</h5>
                        <c:out value="${operacionCategoria}"/>
                    </div>
                </c:if>
                <% request.getSession().removeAttribute("operacionCategoria");%>
                <table class="table table-bordered mb-3">
                    <thead>                  
                        <tr>
                            <th style="width: 10px">#</th>
                            <th>Nombre</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categorias}" var="categoria" varStatus="status">                                        
                            <tr>
                                <td>${categoria.categoriaId}</td>
                                <td>${categoria.nombreCat}</td>
                                <td>
                                    <a href="categorias?accion=productos&idCat=${categoria.categoriaId}" class="btn  btn-info">Productos</a>
                                    <a href="categorias?accion=editar&idCat=${categoria.categoriaId}" class="btn btn-primary">Editar</a>
                                    <form action="categorias" method="post" class="d-inline">
                                        <input type="hidden" name="accion" value="borrar"/>
                                        <input type="hidden" name="idCat" value="${categoria.categoriaId}"/>
                                        <input type="submit" value="Borrar" class="btn btn-danger"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="categorias?accion=nuevo" class="btn btn-primary">Nueva categoria</a>
            </div>
            <!-- /.card-body -->
            <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                    <li class="page-item"><a class="page-link" href="#">«</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">»</a></li>
                </ul>
            </div>
        </div>

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<c:import url="../layouts/dashboard2.jsp"></c:import>
