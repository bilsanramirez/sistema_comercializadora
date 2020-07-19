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
                        <h1>Empleados</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Lista de empleados</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                <c:if test="${operacionEmpleado != null}">
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h5><i class="icon fas fa-check"></i> Mensaje!</h5>
                        <c:out value="${operacionEmpleado}"/>
                    </div>
                </c:if>
                <% request.getSession().removeAttribute("operacionEmpleado");%>
                <table class="table table-bordered mb-3">
                    <thead>                  
                        <tr>
                            <th style="width: 10px">#</th>
                            <th>NOMBRE</th>
                            <th>APELLIDO</th>
                            <th>FECHA_NACIMIENTO</th>
                            <th>EXTENSION</th>
                            <th>REPORTA A</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    
                        <c:forEach items="${empleados}" var="empleado" varStatus="status">                                        
                            <tr>
                                <td>${empleado.empleadoId}</td>
                                <td>${empleado.nombre}</td>
                                <td>${empleado.apellido}</td>
                                <td>${empleado.fechaNac}</td>
                                <td>${empleado.extencion}</td>
                                <td>${empleado.jefe}</td>
                                <td>
                                    <a href="empleados?accion=editar&idEmp=${empleado.empleadoId}" class="btn btn-primary">Editar</a>
                                    <form action="empleados" method="post" class="d-inline">
                                        <input type="hidden" name="accion" value="borrar"/>
                                        <input type="hidden" name="idEmp" value="${empleado.empleadoId}"/>
                                        <input type="submit" value="Borrar" class="btn btn-danger"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    
                </table>
                <a href="empleados?accion=nuevo" class="btn btn-primary">Nuevo empleado</a>
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
