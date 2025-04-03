package com.procergs.quarkus.infra.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value={AuditEntityListener.class})
public abstract class AuditableEntity<I> extends PanacheEntityBase {

    @JsonIgnore
    @Column(name = "CTR_USU_ATU")
    private Long usuarioAtualizacao;

    @JsonIgnore
    @Column(name = "CTR_DTH_ATU")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @Column(name = "CTR_USU_INC")
    private Long usuarioInclusao;

    @JsonIgnore
    @Column(name = "CTR_DTH_INC")
    private LocalDateTime dataInclusao;

    @JsonIgnore
    @Column(name = "CTR_NRO_IP_INC")
    private String ipInclusao;

    @JsonIgnore
    @Column(name = "CTR_NRO_IP_ATU")
    private String ipAtualizacao;

    public abstract I getId();
}
