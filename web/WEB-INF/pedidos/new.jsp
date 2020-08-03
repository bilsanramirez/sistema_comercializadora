<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/dashboard1.jsp"/>
<jsp:useBean class="hn.miapp.comercializadora.modelos.Orden" id="orden" scope="session"></jsp:useBean>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
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
                    <h3 class="card-title">Crear nuevo pedido</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                    <form action="pedidos" method="post">
                        <input type="hidden" name="accion" value="terminarPedido"/>
                        <div class="form-row">
                            <div class="form-group col-sm-6">
                                <div class="form-row">
                                    <div class="form-group col-sm-6">
                                        <label for="fechaOrden">Fecha del pedido:</label>
                                        <input type="text" name="fechaOrden" id="fechaOrden" class="form-control" value="<%= new SimpleDateFormat("dd-MM-yyy").format(orden.getFechaOrden()) %>" disabled="true"/>
                                    </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="empleadoId">Elija un empleado:</label>
                                    <select name="empleadoId" id="empleadoId" class="form-control">
                                        <c:forEach items="${empleados}" var="empleado" varStatus="status">
                                            <option value="${empleado.empleadoId}">${empleado.nombreCompleto}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="clienteId">Elija un cliente:</label>
                                    <select name="clienteId" id="clienteId" class="form-control">
                                        <c:forEach items="${clientes}" var="cliente" varStatus="status">
                                            <option value="${cliente.clienteId}">${cliente.nombreCia}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-6 text-center">
                            <input type="submit" class="btn btn-lg btn-outline-secondary" value="Terminar pedido"/>
                            <h1 style="font-size: 70px">${orden.importeRedondeado}</h1>
                        </div>
                    </div>
                </form>
                <form action="pedidos" method="post">
                    <div class="form-row border-top pt-2">
                        <input type="hidden" name="accion" value="addProducto"/>
                        <div class="form-group col-md-2">
                            <label for="clave">Teclee una clave:</label>
                            <input type="text" name="clave" id="clave" class="form-control"/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="productoId">o seleccione un producto:</label>
                            <select name="productoId" id="productoId" class="form-control">
                                <c:forEach items="${productos}" var="producto" varStatus="status">
                                    <option value="${producto.productoId}">${producto.descripcion}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="cantidad">Cantidad:</label>
                            <input type="number" name="cantidad" id="cantidad" class="form-control"/>
                        </div>
                        <div class="form-group col-md-4">
                            <input type="submit" class="btn btn-primary" value="Agregar producto"/>
                        </div>
                    </div>
                </form>
                <div class="row mt-3">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Cantidad</th>
                                    <th>Precio</th>
                                    <th>Importe</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${orden.detalles}" var="detalle" varStatus="status">
                                    <tr>
                                        <td>${detalle.producto.descripcion}</td>
                                        <td>${detalle.cantidad}</td>
                                        <td>${detalle.producto.precioUnit}</td>
                                        <td>${detalle.importeRedondeado}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- /.card-body -->
            <div class="card-footer clearfix">
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<c:import url="../layouts/dashboard2.jsp"/>