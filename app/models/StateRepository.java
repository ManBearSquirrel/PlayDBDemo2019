package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

public class StateRepository
{
    private JPAApi db;

    @Inject
    public StateRepository(JPAApi db)
    {
        this.db = db;
    }

    public State getState(String stateId)
    {
        TypedQuery<State> statesQuery = db.em().createQuery("SELECT s FROM State s WHERE stateId = :stateId", State.class);
        statesQuery.setParameter("stateId", stateId);
        State state = statesQuery.getSingleResult();

        return state;
    }


}
