package com.egotec.starterproject

import com.egotec.starterproject.entity.TodoEntity
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/todo")
class TodoAPI {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTodo(@PathParam("id") id: String): TodoEntity {
        val state = ThreadState.begin()
        return state.em.find(TodoEntity::class.java, id) ?: throw WebApplicationException(Response.Status.NOT_FOUND)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createTodo(todoEntity: TodoEntity): TodoEntity {
        val state = ThreadState.begin()
        state.em.transaction.begin()
        state.em.merge(todoEntity)
        state.em.transaction.commit()
        return todoEntity
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllTodos(): List<TodoEntity> {
        val state = ThreadState.begin();
        return state.em.createQuery("SELECT todo FROM TodoEntity todo", TodoEntity::class.java).resultList
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    fun updateTodoById(@PathParam("id") id: String, newTodo: TodoEntity): TodoEntity {
        val state = ThreadState.begin()
        val ent = state.em.find(TodoEntity::class.java, id);
        ent.content = newTodo.content
        ent.done = newTodo.done
        state.em.transaction.begin();
        state.em.merge(ent)
        state.em.transaction.commit();
        return ent
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    fun deleteTodoById(@PathParam("id") id: String): TodoEntity {
        val state = ThreadState.begin()
        val ent = state.em.find(TodoEntity::class.java, id);
        state.em.transaction.begin();
        state.em.remove(ent)
        state.em.transaction.commit();
        return ent
    }

}