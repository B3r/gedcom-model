package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.dates.DatePeriod;
import it.marvin_flock.gedcom.dates.DateRange;
import it.marvin_flock.gedcom.dates.DateValue;
import it.marvin_flock.gedcom.dates.GregorianDate;
import it.marvin_flock.gedcom.enums.*;
import it.marvin_flock.gedcom.ordinance.ChildSealOrdinance;
import it.marvin_flock.gedcom.ordinance.SpouseSealing;
import it.marvin_flock.gedcom.records.*;
import it.marvin_flock.gedcom.sources.*;
import it.marvin_flock.gedcom.structures.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
class GedcomModelTest {
    String expectedSimple;

    @BeforeEach
    void setUp() {
        try (InputStream is = new FileInputStream("test/it/marvin_flock/gedcom/resources/simple_upd.ged");
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isReader)) {

            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
                sb.append(System.lineSeparator());
            }
            expectedSimple = sb.toString();
            // remove last line separator
            expectedSimple = StringUtils.removeEnd(expectedSimple, System.lineSeparator());
        } catch (IOException e) {
            log.error("IOException", e);
        }
    }

    /**
     * normally the record list could be build in a loop,
     * this time, to match an already created gedcom file, records are build with predefined ids
     */
    @Test
    void testToString() {
        // --------------------------------
        //         INDI @1@ Record
        // --------------------------------
        IndividualRecord indi_1 = buildIndi_1();

        // --------------------------------
        //         INDI @2@ Record
        // --------------------------------
        IndividualRecord indi_2 = buildIndi_2();

        // --------------------------------
        //         INDI @3@ Record
        // --------------------------------
        IndividualRecord indi_3 = buildIndi_3();

        // --------------------------------
        //          Family Record
        // --------------------------------
        FamilyRecord fam = buildFam();

        // --------------------------------
        //         Submitter Record
        // --------------------------------
        SubmitterRecord subm = buildSubmitter();

        // --------------------------------
        //        Submission Record
        // --------------------------------
        SubmissionRecord subn = buildSubmission(subm.getId());

        // --------------------------------
        //          Source Record
        // --------------------------------
        SourceRecord source = buildSour();

        // --------------------------------
        //           REPO Record
        // --------------------------------
        RepositoryRecord repo = buildRepo();

        // --------------------------------
        //         Family 2 Record
        // --------------------------------
        FamilyRecord fam2 = buildFam2();

        // --------------------------------
        //             HEADER
        // --------------------------------
        Sour.Builder sourBuilder = new Sour.Builder("PAF");
        Sour sour = sourBuilder.withVersion("2.1").build();

        Header.Builder headerBuilder = new Header.Builder(sour, subm.getId(), new Gedc(), "ANSEL");
        headerBuilder.withDestination("ANSTFILE").withSubmissinId(subn.getId());

        GedcomModel.Builder gedcomModelBuilder = new GedcomModel.Builder(headerBuilder.build(), Arrays.asList(indi_1, indi_2, indi_3, fam, subm, source, repo, subn, fam2));
        Assertions.assertEquals(expectedSimple, gedcomModelBuilder.build().toString());
    }

    private RepositoryRecord buildRepo() {
        Addr.Builder addressBuilder = new Addr.Builder().withAddress("35 N West Temple Street Salt Lake City, Utah UT 84150");
        AddressStructure.Builder addressStructureBuilder = new AddressStructure.Builder(addressBuilder.build());
        return new RepositoryRecord.Builder(7,"Family History Library").withAddress(addressStructureBuilder.build()).build();
    }

    private SourceRecord buildSour() {
        GregorianDate from = new GregorianDate();
        from.setMonth(1);
        from.setYear(1820);
        GregorianDate to = new GregorianDate();
        to.setMonth(12);
        to.setYear(1825);
        DatePeriod period = new DatePeriod(from, to);
        RecordedEvent rec = new RecordedEvent(Arrays.asList(EventAttribute.BIRT, EventAttribute.DEAT, EventAttribute.MARR), period, "Madison, Connecticut");
        SourceData sourceData = new SourceData(Collections.singletonList(rec));
        sourceData.setAgency("Madison County Court, State of Connecticut");

        SourceCallNumber call = new SourceCallNumber("13B-1234.01", MediaType.FILM);
        SourceRepositoryCitation repoCit = new SourceRepositoryCitation(7, Collections.singletonList(call));
        return new SourceRecord.Builder(6)
                .withSourceData(sourceData)
                .withTitle("Madison County Birth, Death, and Marriage Records")
                .withAbbreviation("VITAL RECORDS")
                .withSourceRepositoryCitation(repoCit).build();
    }

    private SubmissionRecord buildSubmission(int id) {
        // id for submission record is 8
        SubmissionRecord.Builder submissionBuilder = new SubmissionRecord.Builder(8)
                .withSubmitter(id)
                .withFamilyFilename("Reg Poulson Family")
                .withTempleCode("SLAKE");
        return submissionBuilder.build();
    }

    private SubmitterRecord buildSubmitter() {
        // Last name needs to be in slashes... any way to automate that?
        // Furthermore for this example submitter has id 5
        SubmitterRecord.Builder submitterBuilder = new SubmitterRecord.Builder(5, "Reldon /Poulson/");
        Addr.Builder addressBuilder = new Addr.Builder()
                .withAddress("1900 43rd Street West Billings, MT 68051");
        AddressStructure.Builder asBuilder = new AddressStructure.Builder(addressBuilder.build())
                .withPhones(Collections.singletonList("(406) 555-1232"));
        return submitterBuilder.withAddress(asBuilder.build()).build();
    }

    private FamilyRecord buildFam2() {
        return new FamilyRecord.Builder(9)
                .withHusbandId(1)
                .withChildren(Collections.singletonList(3)).build();
    }

    private FamilyRecord buildFam() {
        GregorianDate greg = new GregorianDate();
        greg.setYear(1859);
        greg.setMonth(12);
        DateValue dv = new DateValue(DateCalendar.DGREGORIAN, greg);

        PlaceStructure.Builder plac = new PlaceStructure.Builder("Rapid City, South Dakota");
        EventDetail.Builder evBuilder = new EventDetail.Builder().withDate(dv).withPlace(plac.build());
        FamilyEventStructure.Builder builder = new FamilyEventStructure.Builder(FamilyEventType.MARR)
                .withEventDetail(evBuilder.build());

        SpouseSealing spouseSealing = new SpouseSealing();
        DateValue slgs_date = new DateValue(DateCalendar.DGREGORIAN, new GregorianDate(1975, 6, 14));
        spouseSealing.setDate(slgs_date);
        spouseSealing.setTempleCode("SLAKE");
        return new FamilyRecord.Builder(4)
                .withEvents(Collections.singletonList(builder.build()))
                .withSpouseSealings(Collections.singletonList(spouseSealing))
                .withHusbandId(1)
                .withWifeId(2)
                .withChildren(Collections.singletonList(3)).build();
    }

    private IndividualRecord buildIndi_1() {
        int id = 1;
        NameStructure.Builder nameBuilder = new NameStructure.Builder("Williams", "Robert Eugene");

        DateValue dv = new DateValue(DateCalendar.DGREGORIAN, new GregorianDate(1822, 10, 2), false);
        PlaceStructure.Builder placeBuilder = new PlaceStructure.Builder("Weston, Madison, Connecticut");
        SourceCitationEvent sourceCitationEvent = new SourceCitationEvent(EventAttribute.BIRT, Role.CHIL);
        SourceCitation.Builder sourceCitationBuilder = new SourceCitation.Builder(6).withPage("Sec. 2, p. 45").withEvent(sourceCitationEvent);
        EventDetail.Builder ed = new EventDetail.Builder().withDate(dv).withPlace(placeBuilder.build()).withSourceCitations(Collections.singletonList(sourceCitationBuilder.build()));

        DateValue d_dv = new DateValue(DateCalendar.DGREGORIAN, new GregorianDate(1905, 4, 14), false);
        PlaceStructure.Builder d_pb = new PlaceStructure.Builder("Stamford, Fairfield, CT");
        EventDetail.Builder d_ed = new EventDetail.Builder().withDate(d_dv).withPlace(d_pb.build());

        PlaceStructure.Builder buri_pb = new PlaceStructure.Builder("Spring Hill Cem., Stamford, CT");
        EventDetail.Builder buri_ed = new EventDetail.Builder().withPlace(buri_pb.build());

        GregorianDate from = new GregorianDate();
        from.setYear(1900);

        GregorianDate to = new GregorianDate();
        to.setYear(1905);

        DatePeriod resi_dp = new DatePeriod(from, to);
        DateValue resi_dv = new DateValue(DateCalendar.DGREGORIAN, resi_dp, false);
        Addr.Builder resi_ab = new Addr.Builder().withAddress("73 North Ashley Spencer, Utah UT84991");
        AddressStructure.Builder resi_asb = new AddressStructure.Builder(resi_ab.build());
        EventDetail.Builder resi_ed = new EventDetail.Builder().withAddress(resi_asb.build()).withDate(resi_dv);

        IndividualEventStructure birth_ies = new IndividualEventStructure(IndividualEventType.BIRT, ed.build());
        IndividualEventStructure death_ies = new IndividualEventStructure(IndividualEventType.DEAT, d_ed.build());
        IndividualEventStructure buri_ies = new IndividualEventStructure(IndividualEventType.BURI, buri_ed.build());
        IndividualAttributeStructure resi_ias = new IndividualAttributeStructure(AttributeType.RESI, resi_ed.build());

        SpouseFamilyLink fams = new SpouseFamilyLink(4);
        SpouseFamilyLink fams2 = new SpouseFamilyLink(9);

        return new IndividualRecord.Builder(id)
                .withNames(Collections.singletonList(nameBuilder.build()))
                .withSex(Sex.M)
                .withEvents(Arrays.asList(birth_ies, death_ies, buri_ies))
                .withAttributes(Collections.singletonList(resi_ias))
                .withSpouseFamilyLinks(Arrays.asList(fams, fams2)).build();
    }

    private IndividualRecord buildIndi_2() {
        NameStructure.Builder nameBuilder = new NameStructure.Builder("Wilson", "Mary Ann");

        GregorianDate greg = new GregorianDate();
        greg.setYear(1828);
        DateRange dr = new DateRange(DateType.BEF, greg);
        DateValue dv = new DateValue(DateCalendar.DGREGORIAN, dr, false);
        PlaceStructure.Builder placeBuilder = new PlaceStructure.Builder("Connecticut");
        EventDetail.Builder ed = new EventDetail.Builder().withDate(dv).withPlace(placeBuilder.build());

        IndividualEventStructure indiStruct = new IndividualEventStructure(IndividualEventType.BIRT, ed.build());
        return new IndividualRecord.Builder(2)
                .withNames(Collections.singletonList(nameBuilder.build()))
                .withSex(Sex.F)
                .withEvents(Collections.singletonList(indiStruct))
                .withSpouseFamilyLinks(Collections.singletonList(new SpouseFamilyLink(4))).build();
    }

    private IndividualRecord buildIndi_3() {
        NameStructure.Builder nameBuilder = new NameStructure.Builder("Williams", "Joe");


        DateValue dv = new DateValue(DateCalendar.DGREGORIAN, new GregorianDate(1861, 6, 11), false);
        PlaceStructure.Builder placeBuilder = new PlaceStructure.Builder("Idaho Falls, Bonneville, Idaho");
        EventDetail.Builder ed = new EventDetail.Builder()
                .withDate(dv)
                .withPlace(placeBuilder.build());

        ChildFamilyLink cfl = new ChildFamilyLink(4);
        ChildFamilyLink cfl2 = new ChildFamilyLink(9, Pedigree.ADOPTED);
        IndividualEventStructure indiStruct = new IndividualEventStructure(IndividualEventType.BIRT, ed.build(),4);

        DateValue adop_dv = new DateValue(DateCalendar.DGREGORIAN, new GregorianDate(1864, 3, 16), false);
        EventDetail.Builder adop_ed = new EventDetail.Builder()
                .withDate(adop_dv);

        IndividualEventStructure adop_indiStruct = new IndividualEventStructure(IndividualEventType.ADOP, adop_ed.build(), 9);

        ChildSealOrdinance childSealOrdinance = new ChildSealOrdinance(9);
        DateValue date = new DateValue(DateCalendar.DGREGORIAN, new GregorianDate(1987, 10, 2), false);
        childSealOrdinance.setDate(date);
        childSealOrdinance.setTempleCode("SLAKE");
        return new IndividualRecord.Builder(3)
                .withNames(Collections.singletonList(nameBuilder.build()))
                .withSex(Sex.M)
                .withChildFamilyLinks(Arrays.asList(cfl, cfl2))
                .withEvents(Arrays.asList(indiStruct, adop_indiStruct))
                .withOrdinance(Collections.singletonList(childSealOrdinance)).build();
    }
}