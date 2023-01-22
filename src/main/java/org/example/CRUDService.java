package org.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document(crud.getName()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public CRUD getCRUD(String documentId) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("user_name").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        CRUD crud;
        if(document.exists()) {
            crud = document.toObject(CRUD.class);
            return crud    ;

        }
        System.out.println("Get data");
     return  null;
    }
    public String updateCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document().delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
        public String deleteCRUD(String documentId){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("crud_user").document(documentId).delete();
        return "Successfully deleted" + documentId;
    }



    }
