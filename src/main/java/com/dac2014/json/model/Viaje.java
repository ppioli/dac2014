package com.dac2014.json.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Viaje {

    private String nombre;

    private String descripcion;
    
    private boolean featured;
    
    private boolean active;
    
    private int destino;
    
    private String localidad;
    
    private double precio;
    
    /**
     */
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "viaje")
    private Set<Actividad> actividades = new HashSet<Actividad>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "viaje")
    private Set<Disponibilidad> disponibilidades = new HashSet<Disponibilidad>();

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaVencimiento;

    /**
     */
    
    
    @ManyToMany
    @JoinTable(name="VIA_IMG")
    private Set<Imagen> imagenes = new HashSet<Imagen>();

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Viaje fromJsonToViaje(String json) {
        return new JSONDeserializer<Viaje>()
        .use(null, Viaje.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Viaje> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Viaje> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Viaje> fromJsonArrayToViajes(String json) {
        return new JSONDeserializer<List<Viaje>>()
        .use("values", Viaje.class).deserialize(json);
    }
	
	
	public static String[] paises = {
			"	Common Name	"	,
			"	Afghanistan	"	,
			"	Albania	"	,
			"	Algeria	"	,
			"	Andorra	"	,
			"	Angola	"	,
			"	Antigua and Barbuda	"	,
			"	Argentina	"	,
			"	Armenia	"	,
			"	Australia	"	,
			"	Austria	"	,
			"	Azerbaijan	"	,
			"	Bahamas, The	"	,
			"	Bahrain	"	,
			"	Bangladesh	"	,
			"	Barbados	"	,
			"	Belarus	"	,
			"	Belgium	"	,
			"	Belize	"	,
			"	Benin	"	,
			"	Bhutan	"	,
			"	Bolivia	"	,
			"	Bosnia and Herzegovina	"	,
			"	Botswana	"	,
			"	Brazil	"	,
			"	Brunei	"	,
			"	Bulgaria	"	,
			"	Burkina Faso	"	,
			"	Burundi	"	,
			"	Cambodia	"	,
			"	Cameroon	"	,
			"	Canada	"	,
			"	Cape Verde	"	,
			"	Central African Republic	"	,
			"	Chad	"	,
			"	Chile	"	,
			"	China, People's Republic of	"	,
			"	Colombia	"	,
			"	Comoros	"	,
			"	Congo, (Congo Â– Kinshasa)	"	,
			"	Congo, (Congo Â– Brazzaville)	"	,
			"	Costa Rica	"	,
			"	Cote d'Ivoire (Ivory Coast)	"	,
			"	Croatia	"	,
			"	Cuba	"	,
			"	Cyprus	"	,
			"	Czech Republic	"	,
			"	Denmark	"	,
			"	Djibouti	"	,
			"	Dominica	"	,
			"	Dominican Republic	"	,
			"	Ecuador	"	,
			"	Egypt	"	,
			"	El Salvador	"	,
			"	Equatorial Guinea	"	,
			"	Eritrea	"	,
			"	Estonia	"	,
			"	Ethiopia	"	,
			"	Fiji	"	,
			"	Finland	"	,
			"	France	"	,
			"	Gabon	"	,
			"	Gambia, The	"	,
			"	Georgia	"	,
			"	Germany	"	,
			"	Ghana	"	,
			"	Greece	"	,
			"	Grenada	"	,
			"	Guatemala	"	,
			"	Guinea	"	,
			"	Guinea-Bissau	"	,
			"	Guyana	"	,
			"	Haiti	"	,
			"	Honduras	"	,
			"	Hungary	"	,
			"	Iceland	"	,
			"	India	"	,
			"	Indonesia	"	,
			"	Iran	"	,
			"	Iraq	"	,
			"	Ireland	"	,
			"	Israel	"	,
			"	Italy	"	,
			"	Jamaica	"	,
			"	Japan	"	,
			"	Jordan	"	,
			"	Kazakhstan	"	,
			"	Kenya	"	,
			"	Kiribati	"	,
			"	Korea, North	"	,
			"	Korea, South	"	,
			"	Kuwait	"	,
			"	Kyrgyzstan	"	,
			"	Laos	"	,
			"	Latvia	"	,
			"	Lebanon	"	,
			"	Lesotho	"	,
			"	Liberia	"	,
			"	Libya	"	,
			"	Liechtenstein	"	,
			"	Lithuania	"	,
			"	Luxembourg	"	,
			"	Macedonia	"	,
			"	Madagascar	"	,
			"	Malawi	"	,
			"	Malaysia	"	,
			"	Maldives	"	,
			"	Mali	"	,
			"	Malta	"	,
			"	Marshall Islands	"	,
			"	Mauritania	"	,
			"	Mauritius	"	,
			"	Mexico	"	,
			"	Micronesia	"	,
			"	Moldova	"	,
			"	Monaco	"	,
			"	Mongolia	"	,
			"	Montenegro	"	,
			"	Morocco	"	,
			"	Mozambique	"	,
			"	Myanmar (Burma)	"	,
			"	Namibia	"	,
			"	Nauru	"	,
			"	Nepal	"	,
			"	Netherlands	"	,
			"	New Zealand	"	,
			"	Nicaragua	"	,
			"	Niger	"	,
			"	Nigeria	"	,
			"	Norway	"	,
			"	Oman	"	,
			"	Pakistan	"	,
			"	Palau	"	,
			"	Panama	"	,
			"	Papua New Guinea	"	,
			"	Paraguay	"	,
			"	Peru	"	,
			"	Philippines	"	,
			"	Poland	"	,
			"	Portugal	"	,
			"	Qatar	"	,
			"	Romania	"	,
			"	Russia	"	,
			"	Rwanda	"	,
			"	Saint Kitts and Nevis	"	,
			"	Saint Lucia	"	,
			"	Saint Vincent and the Grenadines	"	,
			"	Samoa	"	,
			"	San Marino	"	,
			"	Sao Tome and Principe	"	,
			"	Saudi Arabia	"	,
			"	Senegal	"	,
			"	Serbia	"	,
			"	Seychelles	"	,
			"	Sierra Leone	"	,
			"	Singapore	"	,
			"	Slovakia	"	,
			"	Slovenia	"	,
			"	Solomon Islands	"	,
			"	Somalia	"	,
			"	South Africa	"	,
			"	Spain	"	,
			"	Sri Lanka	"	,
			"	Sudan	"	,
			"	Suriname	"	,
			"	Swaziland	"	,
			"	Sweden	"	,
			"	Switzerland	"	,
			"	Syria	"	,
			"	Tajikistan	"	,
			"	Tanzania	"	,
			"	Thailand	"	,
			"	Timor-Leste (East Timor)	"	,
			"	Togo	"	,
			"	Tonga	"	,
			"	Trinidad and Tobago	"	,
			"	Tunisia	"	,
			"	Turkey	"	,
			"	Turkmenistan	"	,
			"	Tuvalu	"	,
			"	Uganda	"	,
			"	Ukraine	"	,
			"	United Arab Emirates	"	,
			"	United Kingdom	"	,
			"	United States	"	,
			"	Uruguay	"	,
			"	Uzbekistan	"	,
			"	Vanuatu	"	,
			"	Vatican City	"	,
			"	Venezuela	"	,
			"	Vietnam	"	,
			"	Yemen	"	,
			"	Zambia	"	,
			"	Zimbabwe	"	,
			"	Abkhazia	"	,
			"	China, Republic of (Taiwan)	"	,
			"	Nagorno-Karabakh	"	,
			"	Northern Cyprus	"	,
			"	Pridnestrovie (Transnistria)	"	,
			"	Somaliland	"	,
			"	South Ossetia	"	,
			"	Ashmore and Cartier Islands	"	,
			"	Christmas Island	"	,
			"	Cocos (Keeling) Islands	"	,
			"	Coral Sea Islands	"	,
			"	Heard Island and McDonald Islands	"	,
			"	Norfolk Island	"	,
			"	New Caledonia	"	,
			"	French Polynesia	"	,
			"	Mayotte	"	,
			"	Saint Barthelemy	"	,
			"	Saint Martin	"	,
			"	Saint Pierre and Miquelon	"	,
			"	Wallis and Futuna	"	,
			"	French Southern and Antarctic Lands	"	,
			"	Clipperton Island	"	,
			"	Bouvet Island	"	,
			"	Cook Islands	"	,
			"	Niue	"	,
			"	Tokelau	"	,
			"	Guernsey	"	,
			"	Isle of Man	"	,
			"	Jersey	"	,
			"	Anguilla	"	,
			"	Bermuda	"	,
			"	British Indian Ocean Territory	"	,
			"	British Sovereign Base Areas	"	,
			"	British Virgin Islands	"	,
			"	Cayman Islands	"	,
			"	Falkland Islands (Islas Malvinas)	"	,
			"	Gibraltar	"	,
			"	Montserrat	"	,
			"	Pitcairn Islands	"	,
			"	Saint Helena	"	,
			"	South Georgia & South Sandwich Islands	"	,
			"	Turks and Caicos Islands	"	,
			"	Northern Mariana Islands	"	,
			"	Puerto Rico	"	,
			"	American Samoa	"	,
			"	Baker Island	"	,
			"	Guam	"	,
			"	Howland Island	"	,
			"	Jarvis Island	"	,
			"	Johnston Atoll	"	,
			"	Kingman Reef	"	,
			"	Midway Islands	"	,
			"	Navassa Island	"	,
			"	Palmyra Atoll	"	,
			"	U.S. Virgin Islands	"	,
			"	Wake Island	"	,
			"	Hong Kong	"	,
			"	Macau	"	,
			"	Faroe Islands	"	,
			"	Greenland	"	,
			"	French Guiana	"	,
			"	Guadeloupe	"	,
			"	Martinique	"	,
			"	Reunion	"	,
			"	Aland	"	,
			"	Aruba	"	,
			"	Netherlands Antilles	"	,
			"	Svalbard	"	,
			"	Ascension	"	,
			"	Tristan da Cunha	"	,
			"	Australian Antarctic Territory	"	,
			"	Ross Dependency	"	,
			"	Peter I Island	"	,
			"	Queen Maud Land	"	,
			"	British Antarctic Territory	"
			};
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public String getNombre() {
        return this.nombre;
    }

	public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public String getDescripcion() {
        return this.descripcion;
    }

	public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public boolean isFeatured() {
        return this.featured;
    }

	public void setFeatured(boolean featured) {
        this.featured = featured;
    }

	public boolean isActive() {
        return this.active;
    }

	public void setActive(boolean active) {
        this.active = active;
    }

	public String getDestino() {
		return this.destino<paises.length? paises[destino]:"La luna";
    }

	public void setDestino(int destino) {
        this.destino = destino;
    }

	public String getLocalidad() {
        return this.localidad;
    }

	public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

	public double getPrecio() {
        return this.precio;
    }

	public void setPrecio(double precio) {
        this.precio = precio;
    }

	public Set<Actividad> getActividades() {
        return this.actividades;
    }

	public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

	public Set<Disponibilidad> getDisponibilidades() {
        return this.disponibilidades;
    }

	public void setDisponibilidades(Set<Disponibilidad> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

	public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }

	public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

	public Set<Imagen> getImagenes() {
        return this.imagenes;
    }

	public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
	
}
