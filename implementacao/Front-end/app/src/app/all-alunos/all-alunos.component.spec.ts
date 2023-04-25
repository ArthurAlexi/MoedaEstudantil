import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAlunosComponent } from './all-alunos.component';

describe('AllAlunosComponent', () => {
  let component: AllAlunosComponent;
  let fixture: ComponentFixture<AllAlunosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllAlunosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllAlunosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
