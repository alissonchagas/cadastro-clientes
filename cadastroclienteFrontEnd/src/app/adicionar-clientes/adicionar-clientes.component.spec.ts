import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionarClientesComponent } from './adicionar-clientes.component';

describe('AdicionarClientesComponent', () => {
  let component: AdicionarClientesComponent;
  let fixture: ComponentFixture<AdicionarClientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdicionarClientesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdicionarClientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
