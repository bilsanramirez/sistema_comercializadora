<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Pedido ${orden.ordenId}</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Detalle</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-12">
                        <a href="/sistema_comercializadora/pedidos?accion=verPedidos" class="btn btn-primary"><- Volver a la lista</a>
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="col-md-4">
                        Fecha del pedido:
                        <h4>${orden.fechaOrden}</h4>
                    </div>
                    <div class="col-md-4">
                        Vendedor:
                        <h4>${orden.empleado.nombreCompleto}</h4>
                    </div>
                    <div class="col-md-4">
                        Cliente:
                        <h4>${orden.cliente.nombreCia}</h4>
                    </div>
                </div>
                <table class="table table-bordered mb-3">
                    <thead>                  
                        <tr>
                            <th>PRODUCTO</th>
                            <th>CANTIDAD</th>
                            <th>PRECIO UNITARIO</th>
                            <th>IMPORTE</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orden.detallesOrden}" var="detalle" varStatus="status">
                            <tr>
                                <td>${detalle.producto.descripcion}</td>
                                <td>${detalle.cantidad}</td>
                                <td>${detalle.producto.precioUnit}</td>
                                <td>${detalle.importe}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- /.card-body -->
        </div>

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<c:import url="../layouts/dashboard2.jsp"></c:import>
