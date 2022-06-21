package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.repository.VatTuHoTroRepository;
import com.mycompany.myapp.service.VatTuHoTroQueryService;
import com.mycompany.myapp.service.VatTuHoTroService;
import com.mycompany.myapp.service.criteria.VatTuHoTroCriteria;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.VatTuHoTro}.
 */
@RestController
@RequestMapping("/api")
public class VatTuHoTroResource {

    private final Logger log = LoggerFactory.getLogger(VatTuHoTroResource.class);

    private static final String ENTITY_NAME = "vatTuHoTro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VatTuHoTroService vatTuHoTroService;

    private final VatTuHoTroRepository vatTuHoTroRepository;

    private final VatTuHoTroQueryService vatTuHoTroQueryService;

    public VatTuHoTroResource(
        VatTuHoTroService vatTuHoTroService,
        VatTuHoTroRepository vatTuHoTroRepository,
        VatTuHoTroQueryService vatTuHoTroQueryService
    ) {
        this.vatTuHoTroService = vatTuHoTroService;
        this.vatTuHoTroRepository = vatTuHoTroRepository;
        this.vatTuHoTroQueryService = vatTuHoTroQueryService;
    }

    /**
     * {@code POST  /vat-tu-ho-tros} : Create a new vatTuHoTro.
     *
     * @param vatTuHoTro the vatTuHoTro to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vatTuHoTro, or with status {@code 400 (Bad Request)} if the vatTuHoTro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vat-tu-ho-tros")
    public ResponseEntity<VatTuHoTro> createVatTuHoTro(@RequestBody VatTuHoTro vatTuHoTro) throws URISyntaxException {
        log.debug("REST request to save VatTuHoTro : {}", vatTuHoTro);
        if (vatTuHoTro.getId() != null) {
            throw new BadRequestAlertException("A new vatTuHoTro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VatTuHoTro result = vatTuHoTroService.save(vatTuHoTro);
        return ResponseEntity
            .created(new URI("/api/vat-tu-ho-tros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vat-tu-ho-tros/:id} : Updates an existing vatTuHoTro.
     *
     * @param id the id of the vatTuHoTro to save.
     * @param vatTuHoTro the vatTuHoTro to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vatTuHoTro,
     * or with status {@code 400 (Bad Request)} if the vatTuHoTro is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vatTuHoTro couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vat-tu-ho-tros/{id}")
    public ResponseEntity<VatTuHoTro> updateVatTuHoTro(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VatTuHoTro vatTuHoTro
    ) throws URISyntaxException {
        log.debug("REST request to update VatTuHoTro : {}, {}", id, vatTuHoTro);
        if (vatTuHoTro.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vatTuHoTro.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vatTuHoTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VatTuHoTro result = vatTuHoTroService.update(vatTuHoTro);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vatTuHoTro.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /vat-tu-ho-tros/:id} : Partial updates given fields of an existing vatTuHoTro, field will ignore if it is null
     *
     * @param id the id of the vatTuHoTro to save.
     * @param vatTuHoTro the vatTuHoTro to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vatTuHoTro,
     * or with status {@code 400 (Bad Request)} if the vatTuHoTro is not valid,
     * or with status {@code 404 (Not Found)} if the vatTuHoTro is not found,
     * or with status {@code 500 (Internal Server Error)} if the vatTuHoTro couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/vat-tu-ho-tros/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VatTuHoTro> partialUpdateVatTuHoTro(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VatTuHoTro vatTuHoTro
    ) throws URISyntaxException {
        log.debug("REST request to partial update VatTuHoTro partially : {}, {}", id, vatTuHoTro);
        if (vatTuHoTro.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vatTuHoTro.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vatTuHoTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VatTuHoTro> result = vatTuHoTroService.partialUpdate(vatTuHoTro);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vatTuHoTro.getId().toString())
        );
    }

    /**
     * {@code GET  /vat-tu-ho-tros} : get all the vatTuHoTros.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vatTuHoTros in body.
     */
    @GetMapping("/vat-tu-ho-tros")
    public ResponseEntity<List<VatTuHoTro>> getAllVatTuHoTros(
        VatTuHoTroCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get VatTuHoTros by criteria: {}", criteria);
        Page<VatTuHoTro> page = vatTuHoTroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vat-tu-ho-tros/count} : count all the vatTuHoTros.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/vat-tu-ho-tros/count")
    public ResponseEntity<Long> countVatTuHoTros(VatTuHoTroCriteria criteria) {
        log.debug("REST request to count VatTuHoTros by criteria: {}", criteria);
        return ResponseEntity.ok().body(vatTuHoTroQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /vat-tu-ho-tros/:id} : get the "id" vatTuHoTro.
     *
     * @param id the id of the vatTuHoTro to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vatTuHoTro, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vat-tu-ho-tros/{id}")
    public ResponseEntity<VatTuHoTro> getVatTuHoTro(@PathVariable Long id) {
        log.debug("REST request to get VatTuHoTro : {}", id);
        Optional<VatTuHoTro> vatTuHoTro = vatTuHoTroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vatTuHoTro);
    }

    /**
     * {@code DELETE  /vat-tu-ho-tros/:id} : delete the "id" vatTuHoTro.
     *
     * @param id the id of the vatTuHoTro to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vat-tu-ho-tros/{id}")
    public ResponseEntity<Void> deleteVatTuHoTro(@PathVariable Long id) {
        log.debug("REST request to delete VatTuHoTro : {}", id);
        vatTuHoTroService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
