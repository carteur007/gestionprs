import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupementUpdateComponent } from './groupement-update.component';

describe('GroupementUpdateComponent', () => {
  let component: GroupementUpdateComponent;
  let fixture: ComponentFixture<GroupementUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupementUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupementUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
