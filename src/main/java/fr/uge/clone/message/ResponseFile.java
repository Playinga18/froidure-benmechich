package fr.uge.clone.message;

/**
 * Classe représentant un fichier de réponse.
 * Un objet de cette classe contient les informations suivantes sur un fichier :
 *   son nom
 *   son URL
 *   son type
 *   sa taille
 */
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;

    /**
     * Construit un nouvel objet ResponseFile à partir des informations fournies.
     *
     * @param name le nom du fichier
     * @param url l'URL du fichier
     * @param type le type du fichier
     * @param size la taille du fichier
     */
    public ResponseFile(String name, String url, String type, int size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    /**
     * Retourne le nom du fichier.
     *
     * @return le nom du fichier
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom du fichier.
     *
     * @param name le nouveau nom du fichier
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne l'URL du fichier.
     *
     * @return l'URL du fichier
     */
    public String getUrl() {
        return url;
    }

    /**
     * Modifie l'URL du fichier.
     *
     * @param url la nouvelle URL du fichier
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Retourne le type du fichier.
     *
     * @return le type du fichier
     */
    public String getType() {
        return type;
    }

    /**
     * Modifie le type du fichier.
     *
     * @param type le nouveau type du fichier
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retourne la taille du fichier.
     *
     * @return la taille du fichier
     */
    public long getSize() {
        return size;
    }

    /**
     * Modifie la taille du fichier.
     *
     * @param size la nouvelle taille du fichier, en octets
     */
    public void setSize(int size) {
        this.size = size;
    }
}
