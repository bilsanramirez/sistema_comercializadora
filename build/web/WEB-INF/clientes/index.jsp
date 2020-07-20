<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.logging.SimpleFormatter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/dashboard1.jsp"></c:import>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Clientes</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Listado de clientes</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                <c:if test="${operacionCliente != null}">
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h5><i class="icon fas fa-check"></i> Mensaje!</h5>
                        <c:out value="${operacionCliente}"/>
                    </div>
                </c:if>
                <% request.getSession().removeAttribute("operacionCliente");%>
                <table class="table table-bordered mb-3 table-responsive">
                    <thead>                  
                        <tr>
                            <th style="width: 10px">#</th>
                            <th>CEDULA</th>
                            <th>COMPAÑIA</th>
                            <th>CONTACTO</th>
                            <th>DIRECCION</th>
                            <th>FAX</th>
                            <th>EMAIL</th>
                            <th>CELULAR</th>
                            <th>FIJO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>

                    <c:forEach items="${clientes}" var="cliente" varStatus="status">                                        
                        <tr>
                            <td>${cliente.clienteId}</td>
                            <td>${cliente.cedulaRuc}</td>
                            <td>${cliente.nombreCia}</td>
                            <td>${cliente.nombreContacto}</td>
                            <td>${cliente.direccionCli}</td>
                            <td>${cliente.fax}</td>
                            <td>${cliente.email}</td>
                            <td>${cliente.celular}</td>
                            <td>${cliente.fijo}</td>
                            <td>
                                <a href="clientes?accion=actualizar&idCli=${cliente.clienteId}" class="btn btn-primary">Editar</a>
                                <form action="clientes" method="post" class="d-inline">
                                    <input type="hidden" name="accion" value="borrar"/>
                                    <input type="hidden" name="idCli" value="${cliente.clienteId}"/>
                                    <input type="submit" value="Borrar" class="btn btn-danger"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                <a href="clientes?accion=nuevo" class="btn btn-primary">Nuevo cliente</a>
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
