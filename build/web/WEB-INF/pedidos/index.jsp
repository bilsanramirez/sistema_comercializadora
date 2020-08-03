<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.logging.SimpleFormatter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
    <c:if test="${ordenCreada != null}">
        <div class="alert alert-success alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h5><i class="icon fas fa-check"></i> Mensaje!</h5>
            Se ha creado el pedido con folio ${ordenCreada.ordenId}
        </div>
    </c:if>
    <% request.getSession().removeAttribute("ordenCreada");%>
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Pedidos</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Listado de pedidos</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
                <table class="table table-bordered mb-3">
                    <thead>                  
                        <tr>
                            <th style="width: 10px">#</th>
                            <th>FECHA DE PEDIDO</th>
                            <th>CLIENTE</th>
                            <th>IMPORTE</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaOrdenes}" var="orden" varStatus="status">
                            <tr>
                                <td>${orden.ordenId}</td>
                                <td>${orden.fechaOrden}</td>
                                <td>${orden.cliente.nombreCia}</td>
                                <td>${orden.importe}</td>
                                <td>
                                    <a href="pedidos?accion=verPedido&ordenId=${orden.ordenId}" class="btn btn-primary">Ver detalles</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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
