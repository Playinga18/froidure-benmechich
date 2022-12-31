package fr.uge.clone.message;

/**
 * Classe représentant un message de réponse.
 * Un objet de cette classe contient un message de réponse à une requête.
 */
public class ResponseMessage {

    /** Le message de réponse. */
    private String message;

    /**
     * Construit un nouvel objet ResponseMessage à partir du message de réponse fourni.
     *
     * @param message le message de réponse
     */
    public ResponseMessage(String message) {
        this.message = message;
    }

    /**
     * Retourne le message de réponse.
     *
     * @return le message de réponse
     */
    public String getMessage() {
        return message;
    }

    /**
     * Modifie le message de réponse.
     *
     * @param message le nouveau message de réponse
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
