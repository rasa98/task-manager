package local.taskManager.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import local.taskManager.myList.MyList;
import local.taskManager.task.Task;

import java.io.IOException;

public class CustomTaskDeserializer extends StdDeserializer<Task> {
    private static final long serialVersionUID = 1L;

	public CustomTaskDeserializer() {
        this(null);
    }

    public CustomTaskDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Task deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        
        // Parse fields from the JSON node
        
     // Initialize variables with default values
        int id = 0;
        String name= null;
        int taskOrder= -1;
        int listId = 0;

        // Check for the existence of key-value pairs in the JSON object
        if (node.has("id")) {
            id = node.get("id").asInt();
        }
        if (node.has("title")) {
            name = node.get("title").asText();
        }
        if (node.has("taskOrder")) {
            taskOrder = node.get("taskOrder").asInt();
        }
        if (node.has("parentId")) {
            listId = node.get("parentId").asInt();
        }

        
        // Create a Task object with the parsed data
        Task task = new Task();
        task.setId(id);
        task.setTitle(name);
        task.setTaskOrder(taskOrder);
        // Set other fields
        
        // Create a MyList object with the parsed listId
        if(listId != 0) {
	        MyList myList = new MyList();
	        myList.setId(listId);
	        task.setParentList(myList);
        }        
        
        return task;
    }
}
