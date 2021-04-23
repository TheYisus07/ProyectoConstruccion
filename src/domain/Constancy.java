package domain;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */
public class Constancy {
    private String recognitionType, description, InstitutionalMailReceivers, InstitutionalMailValidator, InstitutionalMailRedPient, regulatoryNote, eventTitle;

    public Constancy(String recognitionType, String description, String InstitutionalMailReceivers, String InstitutionalMailValidator, String InstitutionalMailRedPient, String regulatoryNote, String eventTitle) {
        this.recognitionType = recognitionType;
        this.description = description;
        this.InstitutionalMailReceivers = InstitutionalMailReceivers;
        this.InstitutionalMailValidator = InstitutionalMailValidator;
        this.InstitutionalMailRedPient = InstitutionalMailRedPient;
        this.regulatoryNote = regulatoryNote;
        this.eventTitle = eventTitle;
    }
    
    public Constancy(){}

    public String getRecognitionType() {
        return recognitionType;
    }

    public String getDescription() {
        return description;
    }

    public String getInstitutionalMailReceivers() {
        return InstitutionalMailReceivers;
    }

    public String getInstitutionalMailValidator() {
        return InstitutionalMailValidator;
    }

    public String getInstitutionalMailRedPient() {
        return InstitutionalMailRedPient;
    }

    public String getRegulatoryNote() {
        return regulatoryNote;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setRecognitionType(String recognitionType) {
        this.recognitionType = recognitionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstitutionalMailReceivers(String InstitutionalMailReceivers) {
        this.InstitutionalMailReceivers = InstitutionalMailReceivers;
    }

    public void setInstitutionalMailValidator(String InstitutionalMailValidator) {
        this.InstitutionalMailValidator = InstitutionalMailValidator;
    }

    public void setInstitutionalMailRedPient(String InstitutionalMailRedPient) {
        this.InstitutionalMailRedPient = InstitutionalMailRedPient;
    }

    public void setRegulatoryNote(String regulatoryNote) {
        this.regulatoryNote = regulatoryNote;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Override
    public String toString() {
        return "Constancy{" + "recognitionType=" + recognitionType + ", description=" + description + ", InstitutionalMailReceivers=" + InstitutionalMailReceivers + ", InstitutionalMailValidator=" + InstitutionalMailValidator + ", InstitutionalMailRedPient=" + InstitutionalMailRedPient + ", regulatoryNote=" + regulatoryNote + ", eventTitle=" + eventTitle + '}';
    }
   
}