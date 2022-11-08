package com.egotec.starterproject

import com.egotec.starterproject.entity.TodoEntity
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/todo")
class TodoAPI {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTodo(@PathParam("id") id: Long): TodoEntity {
        if (DataStorage.data == null) DataStorage.data = arrayListOf<TodoEntity>(TodoEntity(0L, "Klaus", "Ich bin der Klaus", false))
        return DataStorage.data[id.toInt()]
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createTodo(todoEntity: TodoEntity): TodoEntity {
        if (DataStorage.data == null) DataStorage.data = arrayListOf<TodoEntity>(TodoEntity(1L, "Klaus", "Ich bin der Klaus", false))
        todoEntity.id = (DataStorage.data.size).toLong();
        DataStorage.data.add(todoEntity);
        return todoEntity
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllTodos(): List<TodoEntity> {
        if (DataStorage.data == null) DataStorage.data = arrayListOf<TodoEntity>(TodoEntity(1L, "Klaus", "Ich bin der Klaus", false))
        return DataStorage.data;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    fun updateTodoById(@PathParam("id") id: Long, newTodo: TodoEntity): TodoEntity {
        if (DataStorage.data == null) DataStorage.data = arrayListOf<TodoEntity>(TodoEntity(1L, "Klaus", "Ich bin der Klaus", false))
        DataStorage.data[id.toInt()] = newTodo;
        return newTodo;
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteTodoById(@QueryParam("id") id: Long): TodoEntity {
        if (DataStorage.data == null) DataStorage.data = arrayListOf<TodoEntity>(TodoEntity(1L, "Klaus", "Ich bin der Klaus", false))
        var el = DataStorage.data[id.toInt()];
        DataStorage.data.removeAt(id.toInt());
        return el;
    }

}