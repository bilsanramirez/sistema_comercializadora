<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Productos por categoria</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">CATEGORIA ${categoria.nombreCat}</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                <table class="table table-bordered mb-3">
                    <thead>                  
                        <tr>
                            <th>DESCRIPCION</th>
                            <th>PRECIO</th>
                            <th>EXISTENCIA</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categoria.productos}" var="productoCategoria" varStatus="status">                                        
                            <tr>
                                <td>${productoCategoria.descripcion}</td>
                                <td>${productoCategoria.precioUnit}</td>
                                <td>${productoCategoria.existencia}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<c:import url="../layouts/dashboard2.jsp"></c:import>
