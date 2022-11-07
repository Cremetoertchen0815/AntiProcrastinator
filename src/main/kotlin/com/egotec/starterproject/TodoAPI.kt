package com.egotec.starterproject

import com.egotec.starterproject.entity.TodoEntity
import java.util.Deque
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/todo")
class TodoAPI {

    companion object {
        lateinit var instance: ArrayList<TodoEntity>
    }

    init {
        instance = arrayListOf<TodoEntity>(TodoEntity(1, "Klaus", "Ich bin der Klaus", false));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTodo(@PathParam("id") id: Long): TodoEntity {
        return instance[id.toInt()]!!
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createTodo(todoEntity: TodoEntity): TodoEntity {
        instance.add(todoEntity);
        todoEntity.id = (instance.size - 1).toLong();
        return todoEntity
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllTodos(): List<TodoEntity> {
        return instance;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    fun updateTodoById(@PathParam("id") id: Long, newTodo: TodoEntity): TodoEntity {
        instance[id.toInt()] = newTodo;
        return newTodo;
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    fun deleteTodoById(@PathParam("id") id: Long): TodoEntity {
        var el = instance[id.toInt()];
        instance.removeAt(id.toInt());
        return el;
    }

}