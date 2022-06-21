package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.repository.KetQuaCongTacRepository;
import com.mycompany.myapp.service.KetQuaCongTacQueryService;
import com.mycompany.myapp.service.KetQuaCongTacService;
import com.mycompany.myapp.service.criteria.KetQuaCongTacCriteria;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.KetQuaCongTac}.
 */
@RestController
@RequestMapping("/api")
public class KetQuaCongTacResource {

    private final Logger log = LoggerFactory.getLogger(KetQuaCongTacResource.class);

    private static final String ENTITY_NAME = "ketQuaCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KetQuaCongTacService ketQuaCongTacService;

    private final KetQuaCongTacRepository ketQuaCongTacRepository;

    private final KetQuaCongTacQueryService ketQuaCongTacQueryService;

    public KetQuaCongTacResource(
        KetQuaCongTacService ketQuaCongTacService,
        KetQuaCongTacRepository ketQuaCongTacRepository,
        KetQuaCongTacQueryService ketQuaCongTacQueryService
    ) {
        this.ketQuaCongTacService = ketQuaCongTacService;
        this.ketQuaCongTacRepository = ketQuaCongTacRepository;
        this.ketQuaCongTacQueryService = ketQuaCongTacQueryService;
    }

    /**
     * {@code POST  /ket-qua-cong-tacs} : Create a new ketQuaCongTac.
     *
     * @param ketQuaCongTac the ketQuaCongTac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ketQuaCongTac, or with status {@code 400 (Bad Request)} if the ketQuaCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ket-qua-cong-tacs")
    public ResponseEntity<KetQuaCongTac> createKetQuaCongTac(@RequestBody KetQuaCongTac ketQuaCongTac) throws URISyntaxException {
        log.debug("REST request to save KetQuaCongTac : {}", ketQuaCongTac);
        if (ketQuaCongTac.getId() != null) {
            throw new BadRequestAlertException("A new ketQuaCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KetQuaCongTac result = ketQuaCongTacService.save(ketQuaCongTac);
        return ResponseEntity
            .created(new URI("/api/ket-qua-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ket-qua-cong-tacs/:id} : Updates an existing ketQuaCongTac.
     *
     * @param id the id of the ketQuaCongTac to save.
     * @param ketQuaCongTac the ketQuaCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ketQuaCongTac,
     * or with status {@code 400 (Bad Request)} if the ketQuaCongTac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ketQuaCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ket-qua-cong-tacs/{id}")
    public ResponseEntity<KetQuaCongTac> updateKetQuaCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody KetQuaCongTac ketQuaCongTac
    ) throws URISyntaxException {
        log.debug("REST request to update KetQuaCongTac : {}, {}", id, ketQuaCongTac);
        if (ketQuaCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ketQuaCongTac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ketQuaCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        KetQuaCongTac result = ketQuaCongTacService.update(ketQuaCongTac);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ketQuaCongTac.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ket-qua-cong-tacs/:id} : Partial updates given fields of an existing ketQuaCongTac, field will ignore if it is null
     *
     * @param id the id of the ketQuaCongTac to save.
     * @param ketQuaCongTac the ketQuaCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ketQuaCongTac,
     * or with status {@code 400 (Bad Request)} if the ketQuaCongTac is not valid,
     * or with status {@code 404 (Not Found)} if the ketQuaCongTac is not found,
     * or with status {@code 500 (Internal Server Error)} if the ketQuaCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ket-qua-cong-tacs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<KetQuaCongTac> partialUpdateKetQuaCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody KetQuaCongTac ketQuaCongTac
    ) throws URISyntaxException {
        log.debug("REST request to partial update KetQuaCongTac partially : {}, {}", id, ketQuaCongTac);
        if (ketQuaCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ketQuaCongTac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ketQuaCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<KetQuaCongTac> result = ketQuaCongTacService.partialUpdate(ketQuaCongTac);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ketQuaCongTac.getId().toString())
        );
    }

    /**
     * {@code GET  /ket-qua-cong-tacs} : get all the ketQuaCongTacs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ketQuaCongTacs in body.
     */
    @GetMapping("/ket-qua-cong-tacs")
    public ResponseEntity<List<KetQuaCongTac>> getAllKetQuaCongTacs(
        KetQuaCongTacCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get KetQuaCongTacs by criteria: {}", criteria);
        Page<KetQuaCongTac> page = ketQuaCongTacQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ket-qua-cong-tacs/count} : count all the ketQuaCongTacs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ket-qua-cong-tacs/count")
    public ResponseEntity<Long> countKetQuaCongTacs(KetQuaCongTacCriteria criteria) {
        log.debug("REST request to count KetQuaCongTacs by criteria: {}", criteria);
        return ResponseEntity.ok().body(ketQuaCongTacQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ket-qua-cong-tacs/:id} : get the "id" ketQuaCongTac.
     *
     * @param id the id of the ketQuaCongTac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ketQuaCongTac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ket-qua-cong-tacs/{id}")
    public ResponseEntity<KetQuaCongTac> getKetQuaCongTac(@PathVariable Long id) {
        log.debug("REST request to get KetQuaCongTac : {}", id);
        Optional<KetQuaCongTac> ketQuaCongTac = ketQuaCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ketQuaCongTac);
    }

    /**
     * {@code DELETE  /ket-qua-cong-tacs/:id} : delete the "id" ketQuaCongTac.
     *
     * @param id the id of the ketQuaCongTac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ket-qua-cong-tacs/{id}")
    public ResponseEntity<Void> deleteKetQuaCongTac(@PathVariable Long id) {
        log.debug("REST request to delete KetQuaCongTac : {}", id);
        ketQuaCongTacService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
