package andrefigas.com.github.sports.singleton

import andrefigas.com.github.sports.model.dao.EventsDAOContract
import andrefigas.com.github.sports.model.services.EndPoints

interface AppContract {
    var endPoints: EndPoints
    var eventsDAOContract: EventsDAOContract
}