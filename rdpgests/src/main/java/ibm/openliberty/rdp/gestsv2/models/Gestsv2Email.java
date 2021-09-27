package ibm.openliberty.rdp.gestsv2.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Embeddable
public class Gestsv2Email {
	@Schema(example ="work, personal or smth else")
    @Column(name = "EMAIL_TYPE")
    private String type;

	@Schema(example ="welcome.msk@ibm.com")
    @Column(name = "EMAIL_ADDRESS")
    private String email;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
